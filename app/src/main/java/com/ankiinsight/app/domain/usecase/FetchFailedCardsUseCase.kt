package com.ankiinsight.app.domain.usecase

import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.repository.CardRepository
import javax.inject.Inject

class FetchFailedCardsUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(deckId: Long): List<CardData> =
        cardRepository.fetchFailedCards(deckId)
}
