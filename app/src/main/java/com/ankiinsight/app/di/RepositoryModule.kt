package com.ankiinsight.app.di

import com.ankiinsight.app.data.repository.AnalysisRepositoryImpl
import com.ankiinsight.app.data.repository.CardRepositoryImpl
import com.ankiinsight.app.domain.repository.AnalysisRepository
import com.ankiinsight.app.domain.repository.CardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    @Singleton
    abstract fun bindAnalysisRepository(impl: AnalysisRepositoryImpl): AnalysisRepository
}
