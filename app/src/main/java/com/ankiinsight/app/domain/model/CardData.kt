package com.ankiinsight.app.domain.model

data class CardData(
    val id: Long,
    val deckId: Long,
    val deckName: String,
    val front: String,
    val back: String,
    val easeFactor: Int,
    val due: Long,
    val queue: Int = 0,
    val modelId: Long = 0L
)
