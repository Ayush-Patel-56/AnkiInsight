package com.ankiinsight.app.di;

import com.ankiinsight.app.data.local.AppDatabase;
import com.ankiinsight.app.data.local.dao.ConflictResultDao;
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
public final class DatabaseModule_ProvideConflictResultDaoFactory implements Factory<ConflictResultDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideConflictResultDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ConflictResultDao get() {
    return provideConflictResultDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideConflictResultDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideConflictResultDaoFactory(dbProvider);
  }

  public static ConflictResultDao provideConflictResultDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideConflictResultDao(db));
  }
}
