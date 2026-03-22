package com.ankiinsight.app.domain.usecase;

import com.ankiinsight.app.domain.repository.CardRepository;
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
public final class FetchFailedCardsUseCase_Factory implements Factory<FetchFailedCardsUseCase> {
  private final Provider<CardRepository> cardRepositoryProvider;

  public FetchFailedCardsUseCase_Factory(Provider<CardRepository> cardRepositoryProvider) {
    this.cardRepositoryProvider = cardRepositoryProvider;
  }

  @Override
  public FetchFailedCardsUseCase get() {
    return newInstance(cardRepositoryProvider.get());
  }

  public static FetchFailedCardsUseCase_Factory create(
      Provider<CardRepository> cardRepositoryProvider) {
    return new FetchFailedCardsUseCase_Factory(cardRepositoryProvider);
  }

  public static FetchFailedCardsUseCase newInstance(CardRepository cardRepository) {
    return new FetchFailedCardsUseCase(cardRepository);
  }
}
