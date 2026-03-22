package com.ankiinsight.app.data.local.dao

import androidx.room.*
import com.ankiinsight.app.data.local.entity.GapResultEntity

@Dao
interface GapResultDao {

    @Query("SELECT * FROM gap_results ORDER BY timestamp DESC")
    suspend fun getAll(): List<GapResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<GapResultEntity>)

    @Query("DELETE FROM gap_results")
    suspend fun deleteAll()
}
