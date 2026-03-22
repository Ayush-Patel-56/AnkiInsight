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
public final class TagCardUseCase_Factory implements Factory<TagCardUseCase> {
  private final Provider<CardRepository> cardRepositoryProvider;

  public TagCardUseCase_Factory(Provider<CardRepository> cardRepositoryProvider) {
    this.cardRepositoryProvider = cardRepositoryProvider;
  }

  @Override
  public TagCardUseCase get() {
    return newInstance(cardRepositoryProvider.get());
  }

  public static TagCardUseCase_Factory create(Provider<CardRepository> cardRepositoryProvider) {
    return new TagCardUseCase_Factory(cardRepositoryProvider);
  }

  public static TagCardUseCase newInstance(CardRepository cardRepository) {
    return new TagCardUseCase(cardRepository);
  }
}
