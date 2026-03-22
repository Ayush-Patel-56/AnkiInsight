package com.ankiinsight.app.presentation.conflict

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.model.ConflictResult
import com.ankiinsight.app.domain.usecase.DetectConflictsUseCase
import com.ankiinsight.app.domain.usecase.TagCardUseCase
import com.ankiinsight.app.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConflictDetectorViewModel @Inject constructor(
    private val detectConflictsUseCase: DetectConflictsUseCase,
    private val tagCardUseCase: TagCardUseCase
) : ViewModel() {

    private val _conflictsState = MutableStateFlow<UiState<List<ConflictResult>>>(UiState.Empty)
    val conflictsState: StateFlow<UiState<List<ConflictResult>>> = _conflictsState

    private val _scanProgress = MutableStateFlow(0)
    val scanProgress: StateFlow<Int> = _scanProgress

    private val _tagState = MutableStateFlow<String?>(null)
    val tagState: StateFlow<String?> = _tagState

    fun scan(forceRefresh: Boolean = true) {
        viewModelScope.launch {
            _conflictsState.value = UiState.Loading
            _scanProgress.value = 0
            val conflicts = detectConflictsUseCase(forceRefresh)
            _scanProgress.value = 100
            _conflictsState.value = if (conflicts.isEmpty()) UiState.Empty
            else UiState.Success(conflicts)
        }
    }

    fun tagConflict(conflict: ConflictResult) {
        viewModelScope.launch {
            tagCardUseCase(conflict.cardA, "conflicted")
            tagCardUseCase(conflict.cardB, "conflicted")
            _tagState.value = "Tagged: ${conflict.cardA.front.take(30)}…"
        }
    }

    fun tagAllConflicts(conflicts: List<ConflictResult>) {
        viewModelScope.launch {
            conflicts.forEach { conflict ->
                tagCardUseCase(conflict.cardA, "conflicted")
                tagCardUseCase(conflict.cardB, "conflicted")
            }
            _tagState.value = "Tagged ${conflicts.size} conflicts"
        }
    }

    fun clearTagState() {
        _tagState.value = null
    }
}
