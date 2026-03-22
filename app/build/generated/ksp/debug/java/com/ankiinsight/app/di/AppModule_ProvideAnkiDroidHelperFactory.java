package com.ankiinsight.app.di;

import android.content.ContentResolver;
import android.content.Context;
import com.ankiinsight.app.data.ankidroid.AnkiDroidHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideAnkiDroidHelperFactory implements Factory<AnkiDroidHelper> {
  private final Provider<Context> contextProvider;

  private final Provider<ContentResolver> contentResolverProvider;

  public AppModule_ProvideAnkiDroidHelperFactory(Provider<Context> contextProvider,
      Provider<ContentResolver> contentResolverProvider) {
    this.contextProvider = contextProvider;
    this.contentResolverProvider = contentResolverProvider;
  }

  @Override
  public AnkiDroidHelper get() {
    return provideAnkiDroidHelper(contextProvider.get(), contentResolverProvider.get());
  }

  public static AppModule_ProvideAnkiDroidHelperFactory create(Provider<Context> contextProvider,
      Provider<ContentResolver> contentResolverProvider) {
    return new AppModule_ProvideAnkiDroidHelperFactory(contextProvider, contentResolverProvider);
  }

  public static AnkiDroidHelper provideAnkiDroidHelper(Context context,
      ContentResolver contentResolver) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAnkiDroidHelper(context, contentResolver));
  }
}
