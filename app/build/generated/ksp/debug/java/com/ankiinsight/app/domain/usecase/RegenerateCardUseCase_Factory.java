package com.ankiinsight.app.domain.usecase;

import com.ankiinsight.app.data.remote.GroqApiService;
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
public final class RegenerateCardUseCase_Factory implements Factory<RegenerateCardUseCase> {
  private final Provider<GroqApiService> groqApiServiceProvider;

  public RegenerateCardUseCase_Factory(Provider<GroqApiService> groqApiServiceProvider) {
    this.groqApiServiceProvider = groqApiServiceProvider;
  }

  @Override
  public RegenerateCardUseCase get() {
    return newInstance(groqApiServiceProvider.get());
  }

  public static RegenerateCardUseCase_Factory create(
      Provider<GroqApiService> groqApiServiceProvider) {
    return new RegenerateCardUseCase_Factory(groqApiServiceProvider);
  }

  public static RegenerateCardUseCase newInstance(GroqApiService groqApiService) {
    return new RegenerateCardUseCase(groqApiService);
  }
}
