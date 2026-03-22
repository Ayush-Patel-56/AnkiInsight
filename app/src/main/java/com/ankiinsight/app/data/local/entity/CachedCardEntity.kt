package com.ankiinsight.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_cards")
data class CachedCardEntity(
    @PrimaryKey val id: Long,
    val deckId: Long,
    val deckName: String,
    val front: String,
    val back: String,
    val easeFactor: Int,
    val due: Long,
    val modelId: Long = 0L
)
