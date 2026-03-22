package com.ankiinsight.app.presentation.home;

import com.ankiinsight.app.domain.repository.AnalysisRepository;
import com.ankiinsight.app.domain.repository.CardRepository;
import com.ankiinsight.app.domain.usecase.FetchDecksUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<CardRepository> cardRepositoryProvider;

  private final Provider<AnalysisRepository> analysisRepositoryProvider;

  private final Provider<FetchDecksUseCase> fetchDecksUseCaseProvider;

  public HomeViewModel_Factory(Provider<CardRepository> cardRepositoryProvider,
      Provider<AnalysisRepository> analysisRepositoryProvider,
      Provider<FetchDecksUseCase> fetchDecksUseCaseProvider) {
    this.cardRepositoryProvider = cardRepositoryProvider;
    this.analysisRepositoryProvider = analysisRepositoryProvider;
    this.fetchDecksUseCaseProvider = fetchDecksUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(cardRepositoryProvider.get(), analysisRepositoryProvider.get(), fetchDecksUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<CardRepository> cardRepositoryProvider,
      Provider<AnalysisRepository> analysisRepositoryProvider,
      Provider<FetchDecksUseCase> fetchDecksUseCaseProvider) {
    return new HomeViewModel_Factory(cardRepositoryProvider, analysisRepositoryProvider, fetchDecksUseCaseProvider);
  }

  public static HomeViewModel newInstance(CardRepository cardRepository,
      AnalysisRepository analysisRepository, FetchDecksUseCase fetchDecksUseCase) {
    return new HomeViewModel(cardRepository, analysisRepository, fetchDecksUseCase);
  }
}
