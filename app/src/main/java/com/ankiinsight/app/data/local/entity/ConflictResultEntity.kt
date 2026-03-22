package com.ankiinsight.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conflict_results")
data class ConflictResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cardAId: Long,
    val cardBId: Long,
    val type: String,
    val explanation: String,
    val timestamp: Long
)
