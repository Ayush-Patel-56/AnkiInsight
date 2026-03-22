package com.ankiinsight.app.domain.usecase

import com.ankiinsight.app.domain.repository.CardRepository
import javax.inject.Inject

class FetchDecksUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(): Map<Long, String> = cardRepository.fetchAllDecks()
}
