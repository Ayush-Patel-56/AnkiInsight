package com.ankiinsight.app.presentation.insights

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ankiinsight.app.R

public class InsightsFragmentDirections private constructor() {
  public companion object {
    public fun actionInsightsToDeckSelector(): NavDirections =
        ActionOnlyNavDirections(R.id.action_insights_to_deckSelector)
  }
}
