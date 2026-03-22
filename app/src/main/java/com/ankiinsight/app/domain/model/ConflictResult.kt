package com.ankiinsight.app.domain.model

data class ConflictResult(
    val id: Long = 0,
    val cardA: CardData,
    val cardB: CardData,
    val type: String,       // "duplicate" | "contradiction" | "none"
    val explanation: String,
    val timestamp: Long = System.currentTimeMillis()
)
