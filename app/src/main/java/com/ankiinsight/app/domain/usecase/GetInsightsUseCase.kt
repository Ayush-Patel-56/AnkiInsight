package com.ankiinsight.app.domain.usecase

import android.util.Log
import com.ankiinsight.app.data.remote.GroqApiService
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.model.ForgettingPattern
import javax.inject.Inject

data class InsightsData(
    val healthScore: Int,
    val totalCards: Int,
    val failedCards: Int,
    val patterns: List<ForgettingPattern>,
    val weakestTopics: List<Pair<String, Float>>,  // deckName to failRate
    val aiRecommendation: String
)

class GetInsightsUseCase @Inject constructor(
    private val groqApiService: GroqApiService
) {
    suspend operator fun invoke(allCards: List<CardData>): InsightsData {
        val total = allCards.size
        val failed = allCards.count { it.easeFactor < 2000 }
        val healthScore = (100 - (failed.toFloat() / total.coerceAtLeast(1) * 100))
            .toInt().coerceIn(0, 100)

        // Forgetting pattern 1: morning cards (due hour proxy via due date epoch)
        val morningCards = allCards.filter { card ->
            val hour = java.util.Calendar.getInstance().apply {
                timeInMillis = card.due * 1000
            }.get(java.util.Calendar.HOUR_OF_DAY)
            hour in 5..8
        }
        val morningFailed = morningCards.count { it.easeFactor < 2000 }
        val morningRate = if (morningCards.isNotEmpty())
            morningFailed.toFloat() / morningCards.size * 100 else 0f

        val pattern1 = ForgettingPattern(
            label = "Morning",
            description = "You forget ${morningRate.toInt()}% more cards reviewed before 9am",
            value = morningRate
        )

        // Forgetting pattern 2: long answer cards
        val longAnswers = allCards.filter { it.back.split(" ").size >= 20 }
        val shortAnswers = allCards.filter { it.back.split(" ").size < 20 }
        val longFailRate = if (longAnswers.isNotEmpty())
            longAnswers.count { it.easeFactor < 2000 }.toFloat() / longAnswers.size else 0f
        val shortFailRate = if (shortAnswers.isNotEmpty())
            shortAnswers.count { it.easeFactor < 2000 }.toFloat() / shortAnswers.size else 0f
        val multiplier = if (shortFailRate > 0f) longFailRate / shortFailRate else 1f
        val pattern2 = ForgettingPattern(
            label = "Long Answers",
            description = "Cards with 20+ word answers fail ${String.format("%.1f", multiplier)}x more",
            value = longFailRate * 100
        )

        // Weakest topics: per-deck failure rate, top 3
        val byDeck = allCards.groupBy { it.deckName }
        val weakestTopics = byDeck.map { (deckName, cards) ->
            val rate = cards.count { it.easeFactor < 2000 }.toFloat() / cards.size.coerceAtLeast(1)
            Pair(deckName, rate)
        }.sortedByDescending { it.second }.take(3)

        // Single Groq call for AI recommendation
        val statsSummary = """Total cards: $total, Failed: $failed, Health score: $healthScore/100.
Weakest deck: ${weakestTopics.firstOrNull()?.first ?: "N/A"} (${((weakestTopics.firstOrNull()?.second ?: 0f) * 100).toInt()}% fail rate).
Morning failure rate: ${morningRate.toInt()}%."""

        val system = "Give one specific, actionable study recommendation in 2 sentences. Be direct. Do not start with 'Based on the data'."
        val aiRec = groqApiService.complete(system, statsSummary).takeIf {
            it.isNotBlank() && it != "RATE_LIMITED" && it != "API_KEY_MISSING"
        } ?: "Review your weakest deck first and focus on cards with shorter, more memorable answers."

        return InsightsData(
            healthScore = healthScore,
            totalCards = total,
            failedCards = failed,
            patterns = listOf(pattern1, pattern2),
            weakestTopics = weakestTopics,
            aiRecommendation = aiRec
        )
    }
}
