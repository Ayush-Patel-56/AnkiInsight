package com.ankiinsight.app.data.repository;

import com.ankiinsight.app.data.ankidroid.AnkiDroidHelper;
import com.ankiinsight.app.data.local.dao.CachedCardDao;
import com.ankiinsight.app.data.local.dao.ConflictResultDao;
import com.ankiinsight.app.data.local.dao.GapResultDao;
import com.ankiinsight.app.data.remote.GroqApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AnalysisRepositoryImpl_Factory implements Factory<AnalysisRepositoryImpl> {
  private final Provider<AnkiDroidHelper> ankiDroidHelperProvider;

  private final Provider<CachedCardDao> cachedCardDaoProvider;

  private final Provider<ConflictResultDao> conflictResultDaoProvider;

  private final Provider<GapResultDao> gapResultDaoProvider;

  private final Provider<GroqApiService> groqApiServiceProvider;

  public AnalysisRepositoryImpl_Factory(Provider<AnkiDroidHelper> ankiDroidHelperProvider,
      Provider<CachedCardDao> cachedCardDaoProvider,
      Provider<ConflictResultDao> conflictResultDaoProvider,
      Provider<GapResultDao> gapResultDaoProvider,
      Provider<GroqApiService> groqApiServiceProvider) {
    this.ankiDroidHelperProvider = ankiDroidHelperProvider;
    this.cachedCardDaoProvider = cachedCardDaoProvider;
    this.conflictResultDaoProvider = conflictResultDaoProvider;
    this.gapResultDaoProvider = gapResultDaoProvider;
    this.groqApiServiceProvider = groqApiServiceProvider;
  }

  @Override
  public AnalysisRepositoryImpl get() {
    return newInstance(ankiDroidHelperProvider.get(), cachedCardDaoProvider.get(), conflictResultDaoProvider.get(), gapResultDaoProvider.get(), groqApiServiceProvider.get());
  }

  public static AnalysisRepositoryImpl_Factory create(
      Provider<AnkiDroidHelper> ankiDroidHelperProvider,
      Provider<CachedCardDao> cachedCardDaoProvider,
      Provider<ConflictResultDao> conflictResultDaoProvider,
      Provider<GapResultDao> gapResultDaoProvider,
      Provider<GroqApiService> groqApiServiceProvider) {
    return new AnalysisRepositoryImpl_Factory(ankiDroidHelperProvider, cachedCardDaoProvider, conflictResultDaoProvider, gapResultDaoProvider, groqApiServiceProvider);
  }

  public static AnalysisRepositoryImpl newInstance(AnkiDroidHelper ankiDroidHelper,
      CachedCardDao cachedCardDao, ConflictResultDao conflictResultDao, GapResultDao gapResultDao,
      GroqApiService groqApiService) {
    return new AnalysisRepositoryImpl(ankiDroidHelper, cachedCardDao, conflictResultDao, gapResultDao, groqApiService);
  }
}
