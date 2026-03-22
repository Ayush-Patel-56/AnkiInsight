package com.ankiinsight.app.data.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

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
public final class GroqApiService_Factory implements Factory<GroqApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public GroqApiService_Factory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public GroqApiService get() {
    return newInstance(okHttpClientProvider.get());
  }

  public static GroqApiService_Factory create(Provider<OkHttpClient> okHttpClientProvider) {
    return new GroqApiService_Factory(okHttpClientProvider);
  }

  public static GroqApiService newInstance(OkHttpClient okHttpClient) {
    return new GroqApiService(okHttpClient);
  }
}
