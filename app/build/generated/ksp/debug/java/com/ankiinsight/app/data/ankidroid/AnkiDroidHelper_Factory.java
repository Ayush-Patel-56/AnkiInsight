package com.ankiinsight.app.data.ankidroid;

import android.content.ContentResolver;
import android.content.Context;
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
public final class AnkiDroidHelper_Factory implements Factory<AnkiDroidHelper> {
  private final Provider<Context> contextProvider;

  private final Provider<ContentResolver> contentResolverProvider;

  public AnkiDroidHelper_Factory(Provider<Context> contextProvider,
      Provider<ContentResolver> contentResolverProvider) {
    this.contextProvider = contextProvider;
    this.contentResolverProvider = contentResolverProvider;
  }

  @Override
  public AnkiDroidHelper get() {
    return newInstance(contextProvider.get(), contentResolverProvider.get());
  }

  public static AnkiDroidHelper_Factory create(Provider<Context> contextProvider,
      Provider<ContentResolver> contentResolverProvider) {
    return new AnkiDroidHelper_Factory(contextProvider, contentResolverProvider);
  }

  public static AnkiDroidHelper newInstance(Context context, ContentResolver contentResolver) {
    return new AnkiDroidHelper(context, contentResolver);
  }
}
