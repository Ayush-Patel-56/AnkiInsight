package com.ankiinsight.app.presentation.insights;

import com.ankiinsight.app.domain.repository.CardRepository;
import com.ankiinsight.app.domain.usecase.GetInsightsUseCase;
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
public final class InsightsViewModel_Factory implements Factory<InsightsViewModel> {
  private final Provider<CardRepository> cardRepositoryProvider;

  private final Provider<GetInsightsUseCase> getInsightsUseCaseProvider;

  public InsightsViewModel_Factory(Provider<CardRepository> cardRepositoryProvider,
      Provider<GetInsightsUseCase> getInsightsUseCaseProvider) {
    this.cardRepositoryProvider = cardRepositoryProvider;
    this.getInsightsUseCaseProvider = getInsightsUseCaseProvider;
  }

  @Override
  public InsightsViewModel get() {
    return newInstance(cardRepositoryProvider.get(), getInsightsUseCaseProvider.get());
  }

  public static InsightsViewModel_Factory create(Provider<CardRepository> cardRepositoryProvider,
      Provider<GetInsightsUseCase> getInsightsUseCaseProvider) {
    return new InsightsViewModel_Factory(cardRepositoryProvider, getInsightsUseCaseProvider);
  }

  public static InsightsViewModel newInstance(CardRepository cardRepository,
      GetInsightsUseCase getInsightsUseCase) {
    return new InsightsViewModel(cardRepository, getInsightsUseCase);
  }
}
