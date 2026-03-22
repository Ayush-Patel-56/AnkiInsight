package com.ankiinsight.app.domain.usecase

import com.ankiinsight.app.domain.model.GapResult
import com.ankiinsight.app.domain.repository.AnalysisRepository
import javax.inject.Inject

class DetectConceptGapsUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository
) {
    suspend operator fun invoke(deckId: Long, forceRefresh: Boolean = false): List<GapResult> =
        analysisRepository.detectGaps(deckId, forceRefresh)
}
