package com.ankiinsight.app.presentation.gap;

import com.ankiinsight.app.domain.usecase.DetectConceptGapsUseCase;
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
public final class ConceptGapViewModel_Factory implements Factory<ConceptGapViewModel> {
  private final Provider<DetectConceptGapsUseCase> detectConceptGapsUseCaseProvider;

  private final Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider;

  private final Provider<SaveRegeneratedCardUseCase> saveRegeneratedCardUseCaseProvider;

  public ConceptGapViewModel_Factory(
      Provider<DetectConceptGapsUseCase> detectConceptGapsUseCaseProvider,
      Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider,
      Provider<SaveRegeneratedCardUseCase> saveRegeneratedCardUseCaseProvider) {
    this.detectConceptGapsUseCaseProvider = detectConceptGapsUseCaseProvider;
    this.regenerateCardUseCaseProvider = regenerateCardUseCaseProvider;
    this.saveRegeneratedCardUseCaseProvider = saveRegeneratedCardUseCaseProvider;
  }

  @Override
  public ConceptGapViewModel get() {
    return newInstance(detectConceptGapsUseCaseProvider.get(), regenerateCardUseCaseProvider.get(), saveRegeneratedCardUseCaseProvider.get());
  }

  public static ConceptGapViewModel_Factory create(
      Provider<DetectConceptGapsUseCase> detectConceptGapsUseCaseProvider,
      Provider<RegenerateCardUseCase> regenerateCardUseCaseProvider,
      Provider<SaveRegeneratedCardUseCase> saveRegeneratedCardUseCaseProvider) {
    return new ConceptGapViewModel_Factory(detectConceptGapsUseCaseProvider, regenerateCardUseCaseProvider, saveRegeneratedCardUseCaseProvider);
  }

  public static ConceptGapViewModel newInstance(DetectConceptGapsUseCase detectConceptGapsUseCase,
      RegenerateCardUseCase regenerateCardUseCase,
      SaveRegeneratedCardUseCase saveRegeneratedCardUseCase) {
    return new ConceptGapViewModel(detectConceptGapsUseCase, regenerateCardUseCase, saveRegeneratedCardUseCase);
  }
}
