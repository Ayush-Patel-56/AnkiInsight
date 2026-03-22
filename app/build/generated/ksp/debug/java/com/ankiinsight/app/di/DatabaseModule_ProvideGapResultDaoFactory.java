package com.ankiinsight.app.di;

import com.ankiinsight.app.data.local.AppDatabase;
import com.ankiinsight.app.data.local.dao.GapResultDao;
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
public final class DatabaseModule_ProvideGapResultDaoFactory implements Factory<GapResultDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideGapResultDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public GapResultDao get() {
    return provideGapResultDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideGapResultDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideGapResultDaoFactory(dbProvider);
  }

  public static GapResultDao provideGapResultDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideGapResultDao(db));
  }
}
