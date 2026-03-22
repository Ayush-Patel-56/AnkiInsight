package com.ankiinsight.app.di;

import com.ankiinsight.app.data.remote.GroqApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideGroqApiServiceFactory implements Factory<GroqApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public NetworkModule_ProvideGroqApiServiceFactory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public GroqApiService get() {
    return provideGroqApiService(okHttpClientProvider.get());
  }

  public static NetworkModule_ProvideGroqApiServiceFactory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new NetworkModule_ProvideGroqApiServiceFactory(okHttpClientProvider);
  }

  public static GroqApiService provideGroqApiService(OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideGroqApiService(okHttpClient));
  }
}
