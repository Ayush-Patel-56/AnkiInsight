package com.ankiinsight.app.domain.usecase;

import com.ankiinsight.app.domain.repository.AnalysisRepository;
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
public final class DetectConflictsUseCase_Factory implements Factory<DetectConflictsUseCase> {
  private final Provider<AnalysisRepository> analysisRepositoryProvider;

  public DetectConflictsUseCase_Factory(Provider<AnalysisRepository> analysisRepositoryProvider) {
    this.analysisRepositoryProvider = analysisRepositoryProvider;
  }

  @Override
  public DetectConflictsUseCase get() {
    return newInstance(analysisRepositoryProvider.get());
  }

  public static DetectConflictsUseCase_Factory create(
      Provider<AnalysisRepository> analysisRepositoryProvider) {
    return new DetectConflictsUseCase_Factory(analysisRepositoryProvider);
  }

  public static DetectConflictsUseCase newInstance(AnalysisRepository analysisRepository) {
    return new DetectConflictsUseCase(analysisRepository);
  }
}
