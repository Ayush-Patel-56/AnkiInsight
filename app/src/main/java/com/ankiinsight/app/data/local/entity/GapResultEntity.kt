package com.ankiinsight.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gap_results")
data class GapResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val concept: String,
    val explanation: String,
    val neededFor: String,   // JSON-encoded list of card fronts
    val timestamp: Long
)
