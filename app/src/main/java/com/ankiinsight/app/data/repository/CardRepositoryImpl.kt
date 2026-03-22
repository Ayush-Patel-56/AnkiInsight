package com.ankiinsight.app.data.repository

import android.content.ContentResolver
import android.net.Uri
import com.ankiinsight.app.data.ankidroid.AnkiDroidHelper
import com.ankiinsight.app.data.local.dao.CachedCardDao
import com.ankiinsight.app.data.local.entity.CachedCardEntity
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val ankiDroidHelper: AnkiDroidHelper,
    private val cachedCardDao: CachedCardDao
) : CardRepository {

    override fun isAnkiDroidInstalled(): Boolean = ankiDroidHelper.isAnkiDroidInstalled()

    override fun hasPermission(): Boolean = ankiDroidHelper.hasPermission()

    override suspend fun fetchAllDecks(): Map<Long, String> = withContext(Dispatchers.IO) {
        ankiDroidHelper.fetchAllDecks()
    }

    override suspend fun fetchFailedCards(deckId: Long): List<CardData> =
        withContext(Dispatchers.IO) {
            val failed = ankiDroidHelper.fetchFailedCards(deckId)
            // Cache to Room
            cachedCardDao.insertAll(failed.map { it.toEntity() })
            failed
        }

    override suspend fun fetchAllCards(): List<CardData> = withContext(Dispatchers.IO) {
        val all = ankiDroidHelper.fetchAllCardsAcrossDecks()
        cachedCardDao.insertAll(all.map { it.toEntity() })
        all
    }

    override suspend fun fetchCardsByDeck(deckId: Long): List<CardData> =
        withContext(Dispatchers.IO) {
            // Try cache first
            val cached = cachedCardDao.getCardsByDeck(deckId)
            if (cached.isNotEmpty()) return@withContext cached.map { it.toDomain() }
            // Otherwise load from AnkiDroid
            val cards = ankiDroidHelper.fetchFailedCards(deckId)
            cachedCardDao.insertAll(cards.map { it.toEntity() })
            cards
        }

    override suspend fun insertNote(
        deckId: Long, modelId: Long, front: String, back: String, tags: String
    ): Uri? = withContext(Dispatchers.IO) {
        ankiDroidHelper.insertNote(deckId, modelId, front, back, tags)
    }

    override suspend fun tagCard(card: CardData, tag: String): Boolean =
        withContext(Dispatchers.IO) {
            ankiDroidHelper.tagCard(card, tag)
        }

    override suspend fun getCachedTotalCount(): Int = cachedCardDao.getTotalCount()

    override suspend fun getCachedFailedCount(): Int = cachedCardDao.getFailedCount()

    override suspend fun getDeckCount(): Int = cachedCardDao.getDistinctDeckIds().size

    // Mapping extensions
    private fun CardData.toEntity() = CachedCardEntity(
        id = id, deckId = deckId, deckName = deckName,
        front = front, back = back, easeFactor = easeFactor, due = due,
        modelId = modelId
    )

    private fun CachedCardEntity.toDomain() = CardData(
        id = id, deckId = deckId, deckName = deckName,
        front = front, back = back, easeFactor = easeFactor, due = due,
        modelId = modelId
    )
}
