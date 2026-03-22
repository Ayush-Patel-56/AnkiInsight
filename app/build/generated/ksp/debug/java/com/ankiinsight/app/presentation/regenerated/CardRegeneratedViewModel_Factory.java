package com.ankiinsight.app.presentation.regenerated;

import com.ankiinsight.app.domain.usecase.RegenerateCardUseCase;
import com.ankiinsight.app.domain.usecase.SaveRegeneratedCardUseCase;
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
public final class CardRegeneratedViewModel_Factory implements Factory<CardRegeneratedViewModel> {
  private final Provider<SaveRegeneratedCardUseCase> saveRegeneratedCardUseCaseProvider;

  private final Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider;

  public CardRegeneratedViewModel_Factory(
      Provider<SaveRegeneratedCardUseCase> saveRegeneratedCardUseCaseProvider,
      Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider) {
    this.saveRegeneratedCardUseCaseProvider = saveRegeneratedCardUseCaseProvider;
    this.regenerateCardUseCaseProvider = regenerateCardUseCaseProvider;
  }

  @Override
  public CardRegeneratedViewModel get() {
    return newInstance(saveRegeneratedCardUseCaseProvider.get(), regenerateCardUseCaseProvider.get());
  }

  public static CardRegeneratedViewModel_Factory create(
      Provider<SaveRegeneratedCardUseCase> saveRegeneratedCardUseCaseProvider,
      Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider) {
    return new CardRegeneratedViewModel_Factory(saveRegeneratedCardUseCaseProvider, regenerateCardUseCaseProvider);
  }

  public static CardRegeneratedViewModel newInstance(
      SaveRegeneratedCardUseCase saveRegeneratedCardUseCase,
      RegenerateCardUseCase regenerateCardUseCase) {
    return new CardRegeneratedViewModel(saveRegeneratedCardUseCase, regenerateCardUseCase);
  }
}
