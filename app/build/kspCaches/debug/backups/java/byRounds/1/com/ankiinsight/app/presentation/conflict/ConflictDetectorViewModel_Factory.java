package com.ankiinsight.app.presentation.conflict;

import com.ankiinsight.app.domain.usecase.DetectConflictsUseCase;
import com.ankiinsight.app.domain.usecase.TagCardUseCase;
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
public final class ConflictDetectorViewModel_Factory implements Factory<ConflictDetectorViewModel> {
  private final Provider<DetectConflictsUseCase> detectConflictsUseCaseProvider;

  private final Provider<TagCardUseCase> tagCardUseCaseProvider;

  public ConflictDetectorViewModel_Factory(
      Provider<DetectConflictsUseCase> detectConflictsUseCaseProvider,
      Provider<TagCardUseCase> tagCardUseCaseProvider) {
    this.detectConflictsUseCaseProvider = detectConflictsUseCaseProvider;
    this.tagCardUseCaseProvider = tagCardUseCaseProvider;
  }

  @Override
  public ConflictDetectorViewModel get() {
    return newInstance(detectConflictsUseCaseProvider.get(), tagCardUseCaseProvider.get());
  }

  public static ConflictDetectorViewModel_Factory create(
      Provider<DetectConflictsUseCase> detectConflictsUseCaseProvider,
      Provider<TagCardUseCase> tagCardUseCaseProvider) {
    return new ConflictDetectorViewModel_Factory(detectConflictsUseCaseProvider, tagCardUseCaseProvider);
  }

  public static ConflictDetectorViewModel newInstance(DetectConflictsUseCase detectConflictsUseCase,
      TagCardUseCase tagCardUseCase) {
    return new ConflictDetectorViewModel(detectConflictsUseCase, tagCardUseCase);
  }
}
