package com.ankiinsight.app.domain.repository

import com.ankiinsight.app.domain.model.ConflictResult
import com.ankiinsight.app.domain.model.GapResult

interface AnalysisRepository {
    suspend fun detectConflicts(forceRefresh: Boolean = false): List<ConflictResult>
    suspend fun detectGaps(deckId: Long, forceRefresh: Boolean = false): List<GapResult>
    suspend fun getCachedConflictCount(): Int
}
