package com.ankiinsight.app.data.local.dao

import androidx.room.*
import com.ankiinsight.app.data.local.entity.ConflictResultEntity

@Dao
interface ConflictResultDao {

    @Query("SELECT * FROM conflict_results ORDER BY timestamp DESC")
    suspend fun getAll(): List<ConflictResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<ConflictResultEntity>)

    @Query("DELETE FROM conflict_results")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM conflict_results")
    suspend fun getCount(): Int
}
