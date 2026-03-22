package com.ankiinsight.app.presentation.regenerated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.model.CardData
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
class CardRegeneratedViewModel @Inject constructor(
    private val saveRegeneratedCardUseCase: SaveRegeneratedCardUseCase,
    private val regenerateCardUseCase: RegenerateCardUseCase
) : ViewModel() {

    private val _saveState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val saveState: StateFlow<UiState<Boolean>> = _saveState

    private val _retryState = MutableStateFlow<UiState<RegeneratedCard>?>(null)
    val retryState: StateFlow<UiState<RegeneratedCard>?> = _retryState

    fun saveCard(originalCard: CardData, newFront: String, newBack: String) {
        viewModelScope.launch {
            _saveState.value = UiState.Loading
            try {
                val ok = saveRegeneratedCardUseCase(originalCard, newFront, newBack)
                _saveState.value = if (ok) UiState.Success(true)
                else UiState.Error("AnkiDroid rejected the insert. Card may be duplicate.")
            } catch (e: Exception) {
                _saveState.value = UiState.Error(e.message ?: "Failed to save card to AnkiDroid")
            }
        }
    }

    fun retry(card: CardData) {
        viewModelScope.launch {
            _retryState.value = UiState.Loading
            val result = regenerateCardUseCase(card)
            _retryState.value = if (result != null) UiState.Success(result)
            else UiState.Error("Could not regenerate again. Try later.")
        }
    }

    fun clearRetryState() {
        _retryState.value = null
    }
}
