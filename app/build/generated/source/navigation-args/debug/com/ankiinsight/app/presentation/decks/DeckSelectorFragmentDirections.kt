package com.ankiinsight.app.presentation.decks

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ankiinsight.app.R
import kotlin.Int
import kotlin.Long
import kotlin.String

public class DeckSelectorFragmentDirections private constructor() {
  private data class ActionDeckSelectorToFailureAnalyser(
    public val deckId: Long,
    public val deckName: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_deckSelector_to_failureAnalyser

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("deckId", this.deckId)
        result.putString("deckName", this.deckName)
        return result
      }
  }

  private data class ActionDeckSelectorToConceptGap(
    public val deckId: Long,
    public val deckName: String,
    public val failedCount: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_deckSelector_to_conceptGap

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("deckId", this.deckId)
        result.putString("deckName", this.deckName)
        result.putInt("failedCount", this.failedCount)
        return result
      }
  }

  public companion object {
    public fun actionDeckSelectorToFailureAnalyser(deckId: Long, deckName: String): NavDirections =
        ActionDeckSelectorToFailureAnalyser(deckId, deckName)

    public fun actionDeckSelectorToConceptGap(
      deckId: Long,
      deckName: String,
      failedCount: Int,
    ): NavDirections = ActionDeckSelectorToConceptGap(deckId, deckName, failedCount)
  }
}
