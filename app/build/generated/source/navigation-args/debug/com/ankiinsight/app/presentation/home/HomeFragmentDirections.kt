package com.ankiinsight.app.presentation.home

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ankiinsight.app.R

public class HomeFragmentDirections private constructor() {
  public companion object {
    public fun actionHomeToDeckSelector(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_deckSelector)

    public fun actionHomeToConflictDetector(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_conflictDetector)

    public fun actionHomeToInsights(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_insights)
  }
}
