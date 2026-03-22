package com.ankiinsight.app.domain.usecase

import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.repository.CardRepository
import javax.inject.Inject

class SaveRegeneratedCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(
        originalCard: CardData,
        newFront: String,
        newBack: String
    ): Boolean {
        // Insert new card
        val uri = cardRepository.insertNote(
            deckId = originalCard.deckId,
            modelId = originalCard.modelId,
            front = newFront,
            back = newBack,
            tags = "ai-regenerated"
        )
        if (uri == null) return false
        // Tag original as replaced — limitation documented in AnkiDroidHelper
        cardRepository.tagCard(originalCard, "replaced")
        return true
    }
}
