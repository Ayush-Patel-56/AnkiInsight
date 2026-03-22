package com.ankiinsight.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankiinsight.app.data.local.dao.CachedCardDao
import com.ankiinsight.app.data.local.dao.ConflictResultDao
import com.ankiinsight.app.data.local.dao.GapResultDao
import com.ankiinsight.app.data.local.entity.CachedCardEntity
import com.ankiinsight.app.data.local.entity.ConflictResultEntity
import com.ankiinsight.app.data.local.entity.GapResultEntity

@Database(
    entities = [CachedCardEntity::class, ConflictResultEntity::class, GapResultEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cachedCardDao(): CachedCardDao
    abstract fun conflictResultDao(): ConflictResultDao
    abstract fun gapResultDao(): GapResultDao
}
