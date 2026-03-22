package com.ankiinsight.app.data.repository;

import com.ankiinsight.app.data.ankidroid.AnkiDroidHelper;
import com.ankiinsight.app.data.local.dao.CachedCardDao;
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
public final class CardRepositoryImpl_Factory implements Factory<CardRepositoryImpl> {
  private final Provider<AnkiDroidHelper> ankiDroidHelperProvider;

  private final Provider<CachedCardDao> cachedCardDaoProvider;

  public CardRepositoryImpl_Factory(Provider<AnkiDroidHelper> ankiDroidHelperProvider,
      Provider<CachedCardDao> cachedCardDaoProvider) {
    this.ankiDroidHelperProvider = ankiDroidHelperProvider;
    this.cachedCardDaoProvider = cachedCardDaoProvider;
  }

  @Override
  public CardRepositoryImpl get() {
    return newInstance(ankiDroidHelperProvider.get(), cachedCardDaoProvider.get());
  }

  public static CardRepositoryImpl_Factory create(Provider<AnkiDroidHelper> ankiDroidHelperProvider,
      Provider<CachedCardDao> cachedCardDaoProvider) {
    return new CardRepositoryImpl_Factory(ankiDroidHelperProvider, cachedCardDaoProvider);
  }

  public static CardRepositoryImpl newInstance(AnkiDroidHelper ankiDroidHelper,
      CachedCardDao cachedCardDao) {
    return new CardRepositoryImpl(ankiDroidHelper, cachedCardDao);
  }
}
