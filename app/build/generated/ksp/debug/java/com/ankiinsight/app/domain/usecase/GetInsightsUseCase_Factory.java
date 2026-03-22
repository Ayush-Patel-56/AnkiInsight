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
public final class GetInsightsUseCase_Factory implements Factory<GetInsightsUseCase> {
  private final Provider<GroqApiService> groqApiServiceProvider;

  public GetInsightsUseCase_Factory(Provider<GroqApiService> groqApiServiceProvider) {
    this.groqApiServiceProvider = groqApiServiceProvider;
  }

  @Override
  public GetInsightsUseCase get() {
    return newInstance(groqApiServiceProvider.get());
  }

  public static GetInsightsUseCase_Factory create(Provider<GroqApiService> groqApiServiceProvider) {
    return new GetInsightsUseCase_Factory(groqApiServiceProvider);
  }

  public static GetInsightsUseCase newInstance(GroqApiService groqApiService) {
    return new GetInsightsUseCase(groqApiService);
  }
}
