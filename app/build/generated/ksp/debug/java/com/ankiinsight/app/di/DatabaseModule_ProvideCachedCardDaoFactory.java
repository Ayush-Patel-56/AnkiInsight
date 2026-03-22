package com.ankiinsight.app.di;

import com.ankiinsight.app.data.local.AppDatabase;
import com.ankiinsight.app.data.local.dao.CachedCardDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideCachedCardDaoFactory implements Factory<CachedCardDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideCachedCardDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CachedCardDao get() {
    return provideCachedCardDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideCachedCardDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideCachedCardDaoFactory(dbProvider);
  }

  public static CachedCardDao provideCachedCardDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCachedCardDao(db));
  }
}
