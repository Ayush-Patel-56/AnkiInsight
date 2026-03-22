package com.ankiinsight.app.data.repository

import android.util.Log
import com.ankiinsight.app.data.ankidroid.AnkiDroidHelper
import com.ankiinsight.app.data.local.dao.CachedCardDao
import com.ankiinsight.app.data.local.dao.ConflictResultDao
import com.ankiinsight.app.data.local.dao.GapResultDao
import com.ankiinsight.app.data.local.entity.ConflictResultEntity
import com.ankiinsight.app.data.local.entity.GapResultEntity
import com.ankiinsight.app.data.remote.GroqApiService
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.model.ConflictResult
import com.ankiinsight.app.domain.model.GapResult
import com.ankiinsight.app.domain.repository.AnalysisRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnalysisRepositoryImpl @Inject constructor(
    private val ankiDroidHelper: AnkiDroidHelper,
    private val cachedCardDao: CachedCardDao,
    private val conflictResultDao: ConflictResultDao,
    private val gapResultDao: GapResultDao,
    private val groqApiService: GroqApiService
) : AnalysisRepository {

    companion object {
        private const val TAG = "AnalysisRepo"
    }

    private val gson = Gson()

    override suspend fun getCachedConflictCount(): Int = conflictResultDao.getCount()

    override suspend fun detectConflicts(forceRefresh: Boolean): List<ConflictResult> =
        withContext(Dispatchers.IO) {
            if (!forceRefresh) {
                val cached = conflictResultDao.getAll()
                if (cached.isNotEmpty()) {
                    val allCards = cachedCardDao.getAllCards()
                    return@withContext cached.mapNotNull { entity ->
                        val cardA = allCards.find { it.id == entity.cardAId }?.toDomain()
                        val cardB = allCards.find { it.id == entity.cardBId }?.toDomain()
                        if (cardA != null && cardB != null) {
                            ConflictResult(entity.id, cardA, cardB, entity.type, entity.explanation, entity.timestamp)
                        } else null
                    }
                }
            }

            val allCards = ankiDroidHelper.fetchAllCardsAcrossDecks()
            val conflicts = mutableListOf<ConflictResult>()
            val entities = mutableListOf<ConflictResultEntity>()

            // Pre-filter using Jaccard token overlap
            for (i in allCards.indices) {
                for (j in i + 1 until allCards.size) {
                    val a = allCards[i]
                    val b = allCards[j]
                    if (a.deckId == b.deckId) continue  // only cross-deck pairs
                    if (jaccardOverlap(a.front, b.front) <= 0.3f) continue

                    val result = analyseConflictWithGroq(a, b) ?: continue
                    if (result.type == "none") continue

                    conflicts.add(result)
                    entities.add(
                        ConflictResultEntity(
                            cardAId = a.id, cardBId = b.id,
                            type = result.type,
                            explanation = result.explanation,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }

            conflictResultDao.deleteAll()
            conflictResultDao.insertAll(entities)
            conflicts
        }

    override suspend fun detectGaps(deckId: Long, forceRefresh: Boolean): List<GapResult> =
        withContext(Dispatchers.IO) {
            if (!forceRefresh) {
                val cached = gapResultDao.getAll()
                if (cached.isNotEmpty()) return@withContext cached.map { it.toDomain() }
            }

            val failedCards = ankiDroidHelper.fetchFailedCards(deckId)
            val allCards = ankiDroidHelper.fetchAllCardsAcrossDecks()
            val existingFronts = allCards.map { it.front.lowercase() }

            val rawGaps = mutableListOf<GapResult>()
            for (card in failedCards) {
                val gaps = detectGapsForCard(card)
                rawGaps.addAll(gaps)
            }

            // Deduplicate by concept name (case-insensitive)
            val deduped = rawGaps.groupBy { it.concept.lowercase() }
                .map { (_, items) ->
                    items[0].copy(neededFor = items.flatMap { it.neededFor }.distinct())
                }

            // Filter out gaps already covered
            val missing = deduped.filter { gap ->
                existingFronts.none { it.contains(gap.concept.lowercase()) }
            }

            val entities = missing.map {
                GapResultEntity(
                    concept = it.concept,
                    explanation = it.explanation,
                    neededFor = gson.toJson(it.neededFor),
                    timestamp = System.currentTimeMillis()
                )
            }
            gapResultDao.deleteAll()
            gapResultDao.insertAll(entities)
            missing
        }

    private suspend fun detectGapsForCard(card: CardData): List<GapResult> {
        val system = "You are a learning gap analyst. Return ONLY a JSON array."
        val user = """A student keeps failing this flashcard:
Front: ${card.front}
Back: ${card.back}
List prerequisite concepts they must understand to answer this correctly.
Return as JSON array with 'concept' and 'explanation' fields."""

        val raw = groqApiService.complete(system, user)
        if (raw.isBlank() || raw == "RATE_LIMITED" || raw == "API_KEY_MISSING") return emptyList()

        return try {
            val listType = object : TypeToken<List<Map<String, String>>>() {}.type
            val json = extractJsonArray(raw)
            val items: List<Map<String, String>> = gson.fromJson(json, listType)
            items.map { item ->
                GapResult(
                    concept = item["concept"] ?: "",
                    explanation = item["explanation"] ?: "",
                    neededFor = listOf(card.front)
                )
            }.filter { it.concept.isNotBlank() }
        } catch (e: Exception) {
            Log.e(TAG, "detectGapsForCard parse error: ${e.message}")
            emptyList()
        }
    }

    private suspend fun analyseConflictWithGroq(a: CardData, b: CardData): ConflictResult? {
        val system = "You are a flashcard conflict analyser. Return ONLY valid JSON."
        val user = """Card A (${a.deckName}): Q: ${a.front} / A: ${a.back}
Card B (${b.deckName}): Q: ${b.front} / A: ${b.back}
Reply ONLY: { "conflict": bool, "type": "duplicate|contradiction|none", "explanation": "..." }"""

        val raw = groqApiService.complete(system, user)
        if (raw.isBlank() || raw == "RATE_LIMITED" || raw == "API_KEY_MISSING") return null

        return try {
            val json = extractJsonObject(raw)
            val obj = JsonParser.parseString(json).asJsonObject
            val type = obj.get("type")?.asString ?: "none"
            val explanation = obj.get("explanation")?.asString ?: ""
            ConflictResult(cardA = a, cardB = b, type = type, explanation = explanation)
        } catch (e: Exception) {
            Log.e(TAG, "analyseConflictWithGroq parse error: ${e.message}")
            null
        }
    }

    // Jaccard overlap pre-filter (stop words removed)
    internal fun jaccardOverlap(a: String, b: String): Float {
        val stopWords = setOf("what", "is", "the", "a", "an", "of")
        val tokensA = a.lowercase().split(" ").filter { it !in stopWords }.toSet()
        val tokensB = b.lowercase().split(" ").filter { it !in stopWords }.toSet()
        return tokensA.intersect(tokensB).size.toFloat() /
                tokensA.union(tokensB).size.coerceAtLeast(1)
    }

    private fun extractJsonObject(raw: String): String {
        val start = raw.indexOf('{')
        val end = raw.lastIndexOf('}')
        return if (start != -1 && end != -1) raw.substring(start, end + 1) else raw
    }

    private fun extractJsonArray(raw: String): String {
        val start = raw.indexOf('[')
        val end = raw.lastIndexOf(']')
        return if (start != -1 && end != -1) raw.substring(start, end + 1) else raw
    }

    private fun com.ankiinsight.app.data.local.entity.CachedCardEntity.toDomain() = CardData(
        id = id, deckId = deckId, deckName = deckName,
        front = front, back = back, easeFactor = easeFactor, due = due
    )

    private fun GapResultEntity.toDomain(): GapResult {
        val listType = object : TypeToken<List<String>>() {}.type
        val neededForList: List<String> = try {
            gson.fromJson(neededFor, listType)
        } catch (e: Exception) { emptyList() }
        return GapResult(id = id, concept = concept, explanation = explanation,
            neededFor = neededForList, timestamp = timestamp)
    }
}
