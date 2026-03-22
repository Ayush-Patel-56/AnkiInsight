package com.ankiinsight.app.di

import android.content.Context
import androidx.room.Room
import com.ankiinsight.app.data.local.AppDatabase
import com.ankiinsight.app.data.local.dao.CachedCardDao
import com.ankiinsight.app.data.local.dao.ConflictResultDao
import com.ankiinsight.app.data.local.dao.GapResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "anki_insight.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCachedCardDao(db: AppDatabase): CachedCardDao = db.cachedCardDao()

    @Provides
    fun provideConflictResultDao(db: AppDatabase): ConflictResultDao = db.conflictResultDao()

    @Provides
    fun provideGapResultDao(db: AppDatabase): GapResultDao = db.gapResultDao()
}
