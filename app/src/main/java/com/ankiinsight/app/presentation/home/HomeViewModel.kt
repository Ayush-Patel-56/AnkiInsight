package com.ankiinsight.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.repository.AnalysisRepository
import com.ankiinsight.app.domain.repository.CardRepository
import com.ankiinsight.app.domain.usecase.FetchDecksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiState {
    object NotInstalled : HomeUiState()
    object PermissionDenied : HomeUiState()
    data class Connected(
        val totalCards: Int,
        val deckCount: Int,
        val cardsDue: Int,
        val failedToday: Int,
        val conflictsFound: Int
    ) : HomeUiState()
    object Loading : HomeUiState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val analysisRepository: AnalysisRepository,
    private val fetchDecksUseCase: FetchDecksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    fun load() {
        viewModelScope.launch {
            if (!cardRepository.isAnkiDroidInstalled()) {
                _uiState.value = HomeUiState.NotInstalled
                return@launch
            }
            if (!cardRepository.hasPermission()) {
                _uiState.value = HomeUiState.PermissionDenied
                return@launch
            }
            try {
                _uiState.value = HomeUiState.Loading
                val decks = fetchDecksUseCase()
                val allCards = cardRepository.fetchAllCards()
                val totalCards = allCards.size
                val deckCount = decks.size
                val failedToday = allCards.count { it.easeFactor < 2000 || it.queue == 1 || it.queue == 3 }
                val cardsDue = allCards.count { it.due > 0 }
                val conflictsFound = analysisRepository.getCachedConflictCount()

                _uiState.value = HomeUiState.Connected(
                    totalCards = totalCards,
                    deckCount = deckCount,
                    cardsDue = cardsDue,
                    failedToday = failedToday,
                    conflictsFound = conflictsFound
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState.NotInstalled
            }
        }
    }
}
