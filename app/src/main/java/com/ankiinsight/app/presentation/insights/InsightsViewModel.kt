package com.ankiinsight.app.presentation.insights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.repository.CardRepository
import com.ankiinsight.app.domain.usecase.GetInsightsUseCase
import com.ankiinsight.app.domain.usecase.InsightsData
import com.ankiinsight.app.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsightsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val getInsightsUseCase: GetInsightsUseCase
) : ViewModel() {

    private val _insightsState = MutableStateFlow<UiState<InsightsData>>(UiState.Loading)
    val insightsState: StateFlow<UiState<InsightsData>> = _insightsState

    fun load() {
        viewModelScope.launch {
            _insightsState.value = UiState.Loading
            val allCards = cardRepository.fetchAllCards()
            if (allCards.isEmpty()) {
                _insightsState.value = UiState.Empty
                return@launch
            }
            val data = getInsightsUseCase(allCards)
            _insightsState.value = UiState.Success(data)
        }
    }
}
