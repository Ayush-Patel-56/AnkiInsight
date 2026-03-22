package com.ankiinsight.app.domain.model

data class GapResult(
    val id: Long = 0,
    val concept: String,
    val explanation: String,
    val neededFor: List<String>,   // list of card fronts that depend on this concept
    val timestamp: Long = System.currentTimeMillis()
)
