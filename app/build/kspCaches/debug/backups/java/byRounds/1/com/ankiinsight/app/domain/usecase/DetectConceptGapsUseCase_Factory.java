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
public final class DetectConceptGapsUseCase_Factory implements Factory<DetectConceptGapsUseCase> {
  private final Provider<AnalysisRepository> analysisRepositoryProvider;

  public DetectConceptGapsUseCase_Factory(Provider<AnalysisRepository> analysisRepositoryProvider) {
    this.analysisRepositoryProvider = analysisRepositoryProvider;
  }

  @Override
  public DetectConceptGapsUseCase get() {
    return newInstance(analysisRepositoryProvider.get());
  }

  public static DetectConceptGapsUseCase_Factory create(
      Provider<AnalysisRepository> analysisRepositoryProvider) {
    return new DetectConceptGapsUseCase_Factory(analysisRepositoryProvider);
  }

  public static DetectConceptGapsUseCase newInstance(AnalysisRepository analysisRepository) {
    return new DetectConceptGapsUseCase(analysisRepository);
  }
}
