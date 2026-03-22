package com.ankiinsight.app.presentation.gap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.model.GapResult
import com.ankiinsight.app.domain.usecase.DetectConceptGapsUseCase
import com.ankiinsight.app.domain.usecase.RegenerateCardUseCase
import com.ankiinsight.app.domain.usecase.RegeneratedCard
import com.ankiinsight.app.domain.usecase.SaveRegeneratedCardUseCase
import com.ankiinsight.app.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConceptGapViewModel @Inject constructor(
    private val detectConceptGapsUseCase: DetectConceptGapsUseCase,
    private val regenerateCardUseCase: RegenerateCardUseCase,
    private val saveRegeneratedCardUseCase: SaveRegeneratedCardUseCase
) : ViewModel() {

    private val _gapsState = MutableStateFlow<UiState<List<GapResult>>>(UiState.Loading)
    val gapsState: StateFlow<UiState<List<GapResult>>> = _gapsState

    private val _pendingGap = MutableStateFlow<Pair<GapResult, RegeneratedCard>?>(null)
    val pendingGap: StateFlow<Pair<GapResult, RegeneratedCard>?> = _pendingGap

    private var currentDeckId = -1L

    fun loadGaps(deckId: Long) {
        currentDeckId = deckId
        viewModelScope.launch {
            _gapsState.value = UiState.Loading
            val gaps = detectConceptGapsUseCase(deckId)
            _gapsState.value = if (gaps.isEmpty()) UiState.Empty else UiState.Success(gaps)
        }
    }

    fun generateCardForGap(gap: GapResult, deckId: Long) {
        viewModelScope.launch {
            // Use Groq to build a proper card for the missing concept
            val fakeCard = com.ankiinsight.app.domain.model.CardData(
                id = 0, deckId = deckId, deckName = "",
                front = "What is ${gap.concept}?",
                back = gap.explanation,
                easeFactor = 2500, due = 0
            )
            val regen = regenerateCardUseCase(fakeCard)
            if (regen != null) {
                _pendingGap.value = Pair(gap, regen)
            }
        }
    }

    fun confirmSaveGapCard(deckId: Long) {
        val pending = _pendingGap.value ?: return
        viewModelScope.launch {
            saveRegeneratedCardUseCase(
                originalCard = com.ankiinsight.app.domain.model.CardData(
                    id = 0, deckId = deckId, deckName = "",
                    front = "What is ${pending.first.concept}?",
                    back = pending.first.explanation,
                    easeFactor = 2500, due = 0
                ),
                newFront = pending.second.front,
                newBack = pending.second.back
            )
            _pendingGap.value = null
            loadGaps(deckId)
        }
    }

    fun clearPendingGap() {
        _pendingGap.value = null
    }
}
