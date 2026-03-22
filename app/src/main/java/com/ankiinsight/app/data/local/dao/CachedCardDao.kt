package com.ankiinsight.app.data.local.dao

import androidx.room.*
import com.ankiinsight.app.data.local.entity.CachedCardEntity

@Dao
interface CachedCardDao {

    @Query("SELECT * FROM cached_cards")
    suspend fun getAllCards(): List<CachedCardEntity>

    @Query("SELECT * FROM cached_cards WHERE deckId = :deckId")
    suspend fun getCardsByDeck(deckId: Long): List<CachedCardEntity>

    @Query("SELECT * FROM cached_cards WHERE easeFactor < 2000")
    suspend fun getFailedCards(): List<CachedCardEntity>

    @Query("SELECT * FROM cached_cards WHERE deckId = :deckId AND easeFactor < 2000")
    suspend fun getFailedCardsByDeck(deckId: Long): List<CachedCardEntity>

    @Query("SELECT COUNT(*) FROM cached_cards")
    suspend fun getTotalCount(): Int

    @Query("SELECT COUNT(*) FROM cached_cards WHERE easeFactor < 2000")
    suspend fun getFailedCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cards: List<CachedCardEntity>)

    @Query("DELETE FROM cached_cards")
    suspend fun deleteAll()

    @Query("SELECT DISTINCT deckId FROM cached_cards")
    suspend fun getDistinctDeckIds(): List<Long>
}
