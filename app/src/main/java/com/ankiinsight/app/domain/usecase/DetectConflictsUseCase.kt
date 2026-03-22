package com.ankiinsight.app.domain.usecase

import com.ankiinsight.app.domain.model.ConflictResult
import com.ankiinsight.app.domain.repository.AnalysisRepository
import javax.inject.Inject

class DetectConflictsUseCase @Inject constructor(
    private val analysisRepository: AnalysisRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean = false): List<ConflictResult> =
        analysisRepository.detectConflicts(forceRefresh)
}
