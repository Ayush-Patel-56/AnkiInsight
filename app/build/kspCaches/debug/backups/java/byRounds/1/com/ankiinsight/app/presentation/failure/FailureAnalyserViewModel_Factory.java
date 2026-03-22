package com.ankiinsight.app.presentation.failure;

import com.ankiinsight.app.domain.usecase.FetchFailedCardsUseCase;
import com.ankiinsight.app.domain.usecase.RegenerateCardUseCase;
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
public final class FailureAnalyserViewModel_Factory implements Factory<FailureAnalyserViewModel> {
  private final Provider<FetchFailedCardsUseCase> fetchFailedCardsUseCaseProvider;

  private final Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider;

  public FailureAnalyserViewModel_Factory(
      Provider<FetchFailedCardsUseCase> fetchFailedCardsUseCaseProvider,
      Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider) {
    this.fetchFailedCardsUseCaseProvider = fetchFailedCardsUseCaseProvider;
    this.regenerateCardUseCaseProvider = regenerateCardUseCaseProvider;
  }

  @Override
  public FailureAnalyserViewModel get() {
    return newInstance(fetchFailedCardsUseCaseProvider.get(), regenerateCardUseCaseProvider.get());
  }

  public static FailureAnalyserViewModel_Factory create(
      Provider<FetchFailedCardsUseCase> fetchFailedCardsUseCaseProvider,
      Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider) {
    return new FailureAnalyserViewModel_Factory(fetchFailedCardsUseCaseProvider, regenerateCardUseCaseProvider);
  }

  public static FailureAnalyserViewModel newInstance(
      FetchFailedCardsUseCase fetchFailedCardsUseCase,
      RegenerateCardUseCase regenerateCardUseCase) {
    return new FailureAnalyserViewModel(fetchFailedCardsUseCase, regenerateCardUseCase);
  }
}
