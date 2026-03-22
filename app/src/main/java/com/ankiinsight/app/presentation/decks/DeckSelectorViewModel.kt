package com.ankiinsight.app.presentation.decks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankiinsight.app.domain.repository.CardRepository
import com.ankiinsight.app.domain.usecase.FetchDecksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DeckItem(
    val deckId: Long,
    val deckName: String,
    val totalCards: Int,
    val failedCards: Int
)

@HiltViewModel
class DeckSelectorViewModel @Inject constructor(
    private val fetchDecksUseCase: FetchDecksUseCase,
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _decks = MutableStateFlow<List<DeckItem>>(emptyList())
    val decks: StateFlow<List<DeckItem>> = _decks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var allDecks: List<DeckItem> = emptyList()

    fun loadDecks() {
        viewModelScope.launch {
            _isLoading.value = true
            val deckMap = fetchDecksUseCase()
            val items = deckMap.map { (id, name) ->
                val allCards = cardRepository.fetchCardsByDeck(id)
                val failed = allCards.count { it.easeFactor < 2000 || it.queue == 1 || it.queue == 3 }
                DeckItem(id, name, allCards.size, failed)
            }
            allDecks = items
            _decks.value = items
            _isLoading.value = false
        }
    }

    fun filter(query: String) {
        _decks.value = if (query.isBlank()) allDecks
        else allDecks.filter { it.deckName.contains(query, ignoreCase = true) }
    }
}
