package com.ankiinsight.app.domain.repository

import android.net.Uri
import com.ankiinsight.app.domain.model.CardData

interface CardRepository {
    suspend fun fetchAllDecks(): Map<Long, String>
    suspend fun fetchFailedCards(deckId: Long): List<CardData>
    suspend fun fetchAllCards(): List<CardData>
    suspend fun fetchCardsByDeck(deckId: Long): List<CardData>
    suspend fun insertNote(deckId: Long, modelId: Long, front: String, back: String, tags: String = ""): Uri?
    suspend fun tagCard(card: CardData, tag: String): Boolean
    suspend fun getCachedTotalCount(): Int
    suspend fun getCachedFailedCount(): Int
    suspend fun getDeckCount(): Int
    fun isAnkiDroidInstalled(): Boolean
    fun hasPermission(): Boolean
}
