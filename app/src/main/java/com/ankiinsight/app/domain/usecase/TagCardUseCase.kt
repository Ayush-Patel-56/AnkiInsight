package com.ankiinsight.app.domain.usecase

import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.repository.CardRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class TagCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(card: CardData, tag: String = "conflicted"): Boolean {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        return cardRepository.tagCard(card, "$tag::$date")
    }
}
