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
public final class FetchDecksUseCase_Factory implements Factory<FetchDecksUseCase> {
  private final Provider<CardRepository> cardRepositoryProvider;

  public FetchDecksUseCase_Factory(Provider<CardRepository> cardRepositoryProvider) {
    this.cardRepositoryProvider = cardRepositoryProvider;
  }

  @Override
  public FetchDecksUseCase get() {
    return newInstance(cardRepositoryProvider.get());
  }

  public static FetchDecksUseCase_Factory create(Provider<CardRepository> cardRepositoryProvider) {
    return new FetchDecksUseCase_Factory(cardRepositoryProvider);
  }

  public static FetchDecksUseCase newInstance(CardRepository cardRepository) {
    return new FetchDecksUseCase(cardRepository);
  }
}
