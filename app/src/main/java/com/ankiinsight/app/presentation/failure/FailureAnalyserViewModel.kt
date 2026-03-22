package com.ankiinsight.app.presentation.failure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.domain.usecase.FetchFailedCardsUseCase
import com.ankiinsight.app.domain.usecase.RegenerateCardUseCase
import com.ankiinsight.app.domain.usecase.RegeneratedCard
import com.ankiinsight.app.presentation.common.ErrorCode
import com.ankiinsight.app.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FailureAnalyserViewModel @Inject constructor(
    private val fetchFailedCardsUseCase: FetchFailedCardsUseCase,
    private val regenerateCardUseCase: RegenerateCardUseCase
) : ViewModel() {

    private val _cardsState = MutableStateFlow<UiState<List<CardData>>>(UiState.Loading)
    val cardsState: StateFlow<UiState<List<CardData>>> = _cardsState

    private val _regenerateState = MutableStateFlow<UiState<Pair<CardData, RegeneratedCard>>?>(null)
    val regenerateState: StateFlow<UiState<Pair<CardData, RegeneratedCard>>?> = _regenerateState

    private val _isAnalysingAll = MutableStateFlow(false)
    val isAnalysingAll: StateFlow<Boolean> = _isAnalysingAll

    private var currentDeckId: Long = -1L

    fun loadFailedCards(deckId: Long) {
        currentDeckId = deckId
        viewModelScope.launch {
            _cardsState.value = UiState.Loading
            val cards = fetchFailedCardsUseCase(deckId)
            _cardsState.value = if (cards.isEmpty()) UiState.Empty else UiState.Success(cards)
        }
    }

    fun regenerateCard(card: CardData) {
        viewModelScope.launch {
            _regenerateState.value = UiState.Loading
            try {
                val result = regenerateCardUseCase(card)
                _regenerateState.value = UiState.Success(Pair(card, result))
            } catch (e: Exception) {
                _regenerateState.value = UiState.Error(e.message ?: "Failed to regenerate", ErrorCode.GENERIC)
            }
        }
    }

    fun clearRegenerateState() {
        _regenerateState.value = null
    }
}
