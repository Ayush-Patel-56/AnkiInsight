package com.ankiinsight.app.presentation.decks;

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
public final class DeckSelectorViewModel_Factory implements Factory<DeckSelectorViewModel> {
  private final Provider<FetchDecksUseCase> fetchDecksUseCaseProvider;

  private final Provider<CardRepository> cardRepositoryProvider;

  public DeckSelectorViewModel_Factory(Provider<FetchDecksUseCase> fetchDecksUseCaseProvider,
      Provider<CardRepository> cardRepositoryProvider) {
    this.fetchDecksUseCaseProvider = fetchDecksUseCaseProvider;
    this.cardRepositoryProvider = cardRepositoryProvider;
  }

  @Override
  public DeckSelectorViewModel get() {
    return newInstance(fetchDecksUseCaseProvider.get(), cardRepositoryProvider.get());
  }

  public static DeckSelectorViewModel_Factory create(
      Provider<FetchDecksUseCase> fetchDecksUseCaseProvider,
      Provider<CardRepository> cardRepositoryProvider) {
    return new DeckSelectorViewModel_Factory(fetchDecksUseCaseProvider, cardRepositoryProvider);
  }

  public static DeckSelectorViewModel newInstance(FetchDecksUseCase fetchDecksUseCase,
      CardRepository cardRepository) {
    return new DeckSelectorViewModel(fetchDecksUseCase, cardRepository);
  }
}
