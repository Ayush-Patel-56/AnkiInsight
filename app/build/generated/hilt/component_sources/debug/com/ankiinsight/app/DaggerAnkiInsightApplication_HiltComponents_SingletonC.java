package com.ankiinsight.app;

import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.ankiinsight.app.data.ankidroid.AnkiDroidHelper;
import com.ankiinsight.app.data.local.AppDatabase;
import com.ankiinsight.app.data.local.dao.CachedCardDao;
import com.ankiinsight.app.data.local.dao.ConflictResultDao;
import com.ankiinsight.app.data.local.dao.GapResultDao;
import com.ankiinsight.app.data.remote.GroqApiService;
import com.ankiinsight.app.data.repository.AnalysisRepositoryImpl;
import com.ankiinsight.app.data.repository.CardRepositoryImpl;
import com.ankiinsight.app.di.AppModule_ProvideAnkiDroidHelperFactory;
import com.ankiinsight.app.di.AppModule_ProvideContentResolverFactory;
import com.ankiinsight.app.di.DatabaseModule_ProvideAppDatabaseFactory;
import com.ankiinsight.app.di.DatabaseModule_ProvideCachedCardDaoFactory;
import com.ankiinsight.app.di.DatabaseModule_ProvideConflictResultDaoFactory;
import com.ankiinsight.app.di.DatabaseModule_ProvideGapResultDaoFactory;
import com.ankiinsight.app.di.NetworkModule_ProvideGroqApiServiceFactory;
import com.ankiinsight.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.ankiinsight.app.domain.repository.AnalysisRepository;
import com.ankiinsight.app.domain.repository.CardRepository;
import com.ankiinsight.app.domain.usecase.DetectConceptGapsUseCase;
import com.ankiinsight.app.domain.usecase.DetectConflictsUseCase;
import com.ankiinsight.app.domain.usecase.FetchDecksUseCase;
import com.ankiinsight.app.domain.usecase.FetchFailedCardsUseCase;
import com.ankiinsight.app.domain.usecase.GetInsightsUseCase;
import com.ankiinsight.app.domain.usecase.RegenerateCardUseCase;
import com.ankiinsight.app.domain.usecase.SaveRegeneratedCardUseCase;
import com.ankiinsight.app.domain.usecase.TagCardUseCase;
import com.ankiinsight.app.presentation.MainActivity;
import com.ankiinsight.app.presentation.conflict.ConflictDetectorFragment;
import com.ankiinsight.app.presentation.conflict.ConflictDetectorViewModel;
import com.ankiinsight.app.presentation.conflict.ConflictDetectorViewModel_HiltModules;
import com.ankiinsight.app.presentation.decks.DeckSelectorFragment;
import com.ankiinsight.app.presentation.decks.DeckSelectorViewModel;
import com.ankiinsight.app.presentation.decks.DeckSelectorViewModel_HiltModules;
import com.ankiinsight.app.presentation.failure.FailureAnalyserFragment;
import com.ankiinsight.app.presentation.failure.FailureAnalyserViewModel;
import com.ankiinsight.app.presentation.failure.FailureAnalyserViewModel_HiltModules;
import com.ankiinsight.app.presentation.gap.ConceptGapFragment;
import com.ankiinsight.app.presentation.gap.ConceptGapViewModel;
import com.ankiinsight.app.presentation.gap.ConceptGapViewModel_HiltModules;
import com.ankiinsight.app.presentation.home.HomeFragment;
import com.ankiinsight.app.presentation.home.HomeViewModel;
import com.ankiinsight.app.presentation.home.HomeViewModel_HiltModules;
import com.ankiinsight.app.presentation.insights.InsightsFragment;
import com.ankiinsight.app.presentation.insights.InsightsViewModel;
import com.ankiinsight.app.presentation.insights.InsightsViewModel_HiltModules;
import com.ankiinsight.app.presentation.regenerated.CardRegeneratedFragment;
import com.ankiinsight.app.presentation.regenerated.CardRegeneratedViewModel;
import com.ankiinsight.app.presentation.regenerated.CardRegeneratedViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class DaggerAnkiInsightApplication_HiltComponents_SingletonC {
  private DaggerAnkiInsightApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public AnkiInsightApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements AnkiInsightApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements AnkiInsightApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements AnkiInsightApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements AnkiInsightApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements AnkiInsightApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements AnkiInsightApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements AnkiInsightApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public AnkiInsightApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends AnkiInsightApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends AnkiInsightApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectConflictDetectorFragment(ConflictDetectorFragment conflictDetectorFragment) {
    }

    @Override
    public void injectDeckSelectorFragment(DeckSelectorFragment deckSelectorFragment) {
    }

    @Override
    public void injectFailureAnalyserFragment(FailureAnalyserFragment failureAnalyserFragment) {
    }

    @Override
    public void injectConceptGapFragment(ConceptGapFragment conceptGapFragment) {
    }

    @Override
    public void injectHomeFragment(HomeFragment homeFragment) {
    }

    @Override
    public void injectInsightsFragment(InsightsFragment insightsFragment) {
    }

    @Override
    public void injectCardRegeneratedFragment(CardRegeneratedFragment cardRegeneratedFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends AnkiInsightApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends AnkiInsightApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(7).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_regenerated_CardRegeneratedViewModel, CardRegeneratedViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_gap_ConceptGapViewModel, ConceptGapViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_conflict_ConflictDetectorViewModel, ConflictDetectorViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_decks_DeckSelectorViewModel, DeckSelectorViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_failure_FailureAnalyserViewModel, FailureAnalyserViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_insights_InsightsViewModel, InsightsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_ankiinsight_app_presentation_home_HomeViewModel = "com.ankiinsight.app.presentation.home.HomeViewModel";

      static String com_ankiinsight_app_presentation_decks_DeckSelectorViewModel = "com.ankiinsight.app.presentation.decks.DeckSelectorViewModel";

      static String com_ankiinsight_app_presentation_failure_FailureAnalyserViewModel = "com.ankiinsight.app.presentation.failure.FailureAnalyserViewModel";

      static String com_ankiinsight_app_presentation_conflict_ConflictDetectorViewModel = "com.ankiinsight.app.presentation.conflict.ConflictDetectorViewModel";

      static String com_ankiinsight_app_presentation_regenerated_CardRegeneratedViewModel = "com.ankiinsight.app.presentation.regenerated.CardRegeneratedViewModel";

      static String com_ankiinsight_app_presentation_gap_ConceptGapViewModel = "com.ankiinsight.app.presentation.gap.ConceptGapViewModel";

      static String com_ankiinsight_app_presentation_insights_InsightsViewModel = "com.ankiinsight.app.presentation.insights.InsightsViewModel";

      @KeepFieldType
      HomeViewModel com_ankiinsight_app_presentation_home_HomeViewModel2;

      @KeepFieldType
      DeckSelectorViewModel com_ankiinsight_app_presentation_decks_DeckSelectorViewModel2;

      @KeepFieldType
      FailureAnalyserViewModel com_ankiinsight_app_presentation_failure_FailureAnalyserViewModel2;

      @KeepFieldType
      ConflictDetectorViewModel com_ankiinsight_app_presentation_conflict_ConflictDetectorViewModel2;

      @KeepFieldType
      CardRegeneratedViewModel com_ankiinsight_app_presentation_regenerated_CardRegeneratedViewModel2;

      @KeepFieldType
      ConceptGapViewModel com_ankiinsight_app_presentation_gap_ConceptGapViewModel2;

      @KeepFieldType
      InsightsViewModel com_ankiinsight_app_presentation_insights_InsightsViewModel2;
    }
  }

  private static final class ViewModelCImpl extends AnkiInsightApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<CardRegeneratedViewModel> cardRegeneratedViewModelProvider;

    private Provider<ConceptGapViewModel> conceptGapViewModelProvider;

    private Provider<ConflictDetectorViewModel> conflictDetectorViewModelProvider;

    private Provider<DeckSelectorViewModel> deckSelectorViewModelProvider;

    private Provider<FailureAnalyserViewModel> failureAnalyserViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<InsightsViewModel> insightsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private SaveRegeneratedCardUseCase saveRegeneratedCardUseCase() {
      return new SaveRegeneratedCardUseCase(singletonCImpl.bindCardRepositoryProvider.get());
    }

    private RegenerateCardUseCase regenerateCardUseCase() {
      return new RegenerateCardUseCase(singletonCImpl.provideGroqApiServiceProvider.get());
    }

    private DetectConceptGapsUseCase detectConceptGapsUseCase() {
      return new DetectConceptGapsUseCase(singletonCImpl.bindAnalysisRepositoryProvider.get());
    }

    private DetectConflictsUseCase detectConflictsUseCase() {
      return new DetectConflictsUseCase(singletonCImpl.bindAnalysisRepositoryProvider.get());
    }

    private TagCardUseCase tagCardUseCase() {
      return new TagCardUseCase(singletonCImpl.bindCardRepositoryProvider.get());
    }

    private FetchDecksUseCase fetchDecksUseCase() {
      return new FetchDecksUseCase(singletonCImpl.bindCardRepositoryProvider.get());
    }

    private FetchFailedCardsUseCase fetchFailedCardsUseCase() {
      return new FetchFailedCardsUseCase(singletonCImpl.bindCardRepositoryProvider.get());
    }

    private GetInsightsUseCase getInsightsUseCase() {
      return new GetInsightsUseCase(singletonCImpl.provideGroqApiServiceProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.cardRegeneratedViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.conceptGapViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.conflictDetectorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.deckSelectorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.failureAnalyserViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.insightsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(7).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_regenerated_CardRegeneratedViewModel, ((Provider) cardRegeneratedViewModelProvider)).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_gap_ConceptGapViewModel, ((Provider) conceptGapViewModelProvider)).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_conflict_ConflictDetectorViewModel, ((Provider) conflictDetectorViewModelProvider)).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_decks_DeckSelectorViewModel, ((Provider) deckSelectorViewModelProvider)).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_failure_FailureAnalyserViewModel, ((Provider) failureAnalyserViewModelProvider)).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_ankiinsight_app_presentation_insights_InsightsViewModel, ((Provider) insightsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_ankiinsight_app_presentation_failure_FailureAnalyserViewModel = "com.ankiinsight.app.presentation.failure.FailureAnalyserViewModel";

      static String com_ankiinsight_app_presentation_decks_DeckSelectorViewModel = "com.ankiinsight.app.presentation.decks.DeckSelectorViewModel";

      static String com_ankiinsight_app_presentation_regenerated_CardRegeneratedViewModel = "com.ankiinsight.app.presentation.regenerated.CardRegeneratedViewModel";

      static String com_ankiinsight_app_presentation_gap_ConceptGapViewModel = "com.ankiinsight.app.presentation.gap.ConceptGapViewModel";

      static String com_ankiinsight_app_presentation_conflict_ConflictDetectorViewModel = "com.ankiinsight.app.presentation.conflict.ConflictDetectorViewModel";

      static String com_ankiinsight_app_presentation_home_HomeViewModel = "com.ankiinsight.app.presentation.home.HomeViewModel";

      static String com_ankiinsight_app_presentation_insights_InsightsViewModel = "com.ankiinsight.app.presentation.insights.InsightsViewModel";

      @KeepFieldType
      FailureAnalyserViewModel com_ankiinsight_app_presentation_failure_FailureAnalyserViewModel2;

      @KeepFieldType
      DeckSelectorViewModel com_ankiinsight_app_presentation_decks_DeckSelectorViewModel2;

      @KeepFieldType
      CardRegeneratedViewModel com_ankiinsight_app_presentation_regenerated_CardRegeneratedViewModel2;

      @KeepFieldType
      ConceptGapViewModel com_ankiinsight_app_presentation_gap_ConceptGapViewModel2;

      @KeepFieldType
      ConflictDetectorViewModel com_ankiinsight_app_presentation_conflict_ConflictDetectorViewModel2;

      @KeepFieldType
      HomeViewModel com_ankiinsight_app_presentation_home_HomeViewModel2;

      @KeepFieldType
      InsightsViewModel com_ankiinsight_app_presentation_insights_InsightsViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.ankiinsight.app.presentation.regenerated.CardRegeneratedViewModel 
          return (T) new CardRegeneratedViewModel(viewModelCImpl.saveRegeneratedCardUseCase(), viewModelCImpl.regenerateCardUseCase());

          case 1: // com.ankiinsight.app.presentation.gap.ConceptGapViewModel 
          return (T) new ConceptGapViewModel(viewModelCImpl.detectConceptGapsUseCase(), viewModelCImpl.regenerateCardUseCase(), viewModelCImpl.saveRegeneratedCardUseCase());

          case 2: // com.ankiinsight.app.presentation.conflict.ConflictDetectorViewModel 
          return (T) new ConflictDetectorViewModel(viewModelCImpl.detectConflictsUseCase(), viewModelCImpl.tagCardUseCase());

          case 3: // com.ankiinsight.app.presentation.decks.DeckSelectorViewModel 
          return (T) new DeckSelectorViewModel(viewModelCImpl.fetchDecksUseCase(), singletonCImpl.bindCardRepositoryProvider.get());

          case 4: // com.ankiinsight.app.presentation.failure.FailureAnalyserViewModel 
          return (T) new FailureAnalyserViewModel(viewModelCImpl.fetchFailedCardsUseCase(), viewModelCImpl.regenerateCardUseCase());

          case 5: // com.ankiinsight.app.presentation.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.bindCardRepositoryProvider.get(), singletonCImpl.bindAnalysisRepositoryProvider.get(), viewModelCImpl.fetchDecksUseCase());

          case 6: // com.ankiinsight.app.presentation.insights.InsightsViewModel 
          return (T) new InsightsViewModel(singletonCImpl.bindCardRepositoryProvider.get(), viewModelCImpl.getInsightsUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends AnkiInsightApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends AnkiInsightApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends AnkiInsightApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<ContentResolver> provideContentResolverProvider;

    private Provider<AnkiDroidHelper> provideAnkiDroidHelperProvider;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<CardRepositoryImpl> cardRepositoryImplProvider;

    private Provider<CardRepository> bindCardRepositoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<GroqApiService> provideGroqApiServiceProvider;

    private Provider<AnalysisRepositoryImpl> analysisRepositoryImplProvider;

    private Provider<AnalysisRepository> bindAnalysisRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private CachedCardDao cachedCardDao() {
      return DatabaseModule_ProvideCachedCardDaoFactory.provideCachedCardDao(provideAppDatabaseProvider.get());
    }

    private ConflictResultDao conflictResultDao() {
      return DatabaseModule_ProvideConflictResultDaoFactory.provideConflictResultDao(provideAppDatabaseProvider.get());
    }

    private GapResultDao gapResultDao() {
      return DatabaseModule_ProvideGapResultDaoFactory.provideGapResultDao(provideAppDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideContentResolverProvider = DoubleCheck.provider(new SwitchingProvider<ContentResolver>(singletonCImpl, 2));
      this.provideAnkiDroidHelperProvider = DoubleCheck.provider(new SwitchingProvider<AnkiDroidHelper>(singletonCImpl, 1));
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 3));
      this.cardRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindCardRepositoryProvider = DoubleCheck.provider((Provider) cardRepositoryImplProvider);
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 5));
      this.provideGroqApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<GroqApiService>(singletonCImpl, 4));
      this.analysisRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 6);
      this.bindAnalysisRepositoryProvider = DoubleCheck.provider((Provider) analysisRepositoryImplProvider);
    }

    @Override
    public void injectAnkiInsightApplication(AnkiInsightApplication ankiInsightApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.ankiinsight.app.data.repository.CardRepositoryImpl 
          return (T) new CardRepositoryImpl(singletonCImpl.provideAnkiDroidHelperProvider.get(), singletonCImpl.cachedCardDao());

          case 1: // com.ankiinsight.app.data.ankidroid.AnkiDroidHelper 
          return (T) AppModule_ProvideAnkiDroidHelperFactory.provideAnkiDroidHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideContentResolverProvider.get());

          case 2: // android.content.ContentResolver 
          return (T) AppModule_ProvideContentResolverFactory.provideContentResolver(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.ankiinsight.app.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.ankiinsight.app.data.remote.GroqApiService 
          return (T) NetworkModule_ProvideGroqApiServiceFactory.provideGroqApiService(singletonCImpl.provideOkHttpClientProvider.get());

          case 5: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 6: // com.ankiinsight.app.data.repository.AnalysisRepositoryImpl 
          return (T) new AnalysisRepositoryImpl(singletonCImpl.provideAnkiDroidHelperProvider.get(), singletonCImpl.cachedCardDao(), singletonCImpl.conflictResultDao(), singletonCImpl.gapResultDao(), singletonCImpl.provideGroqApiServiceProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
