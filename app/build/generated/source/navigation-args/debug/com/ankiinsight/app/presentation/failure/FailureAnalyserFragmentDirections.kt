package com.ankiinsight.app.presentation.failure

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ankiinsight.app.R
import kotlin.Int
import kotlin.Long
import kotlin.String

public class FailureAnalyserFragmentDirections private constructor() {
  private data class ActionFailureAnalyserToCardRegenerated(
    public val originalFront: String,
    public val originalBack: String,
    public val regenFront: String,
    public val regenBack: String,
    public val cardId: Long,
    public val deckId: Long,
    public val easeFactor: Int,
    public val modelId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_failureAnalyser_to_cardRegenerated

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("originalFront", this.originalFront)
        result.putString("originalBack", this.originalBack)
        result.putString("regenFront", this.regenFront)
        result.putString("regenBack", this.regenBack)
        result.putLong("cardId", this.cardId)
        result.putLong("deckId", this.deckId)
        result.putInt("easeFactor", this.easeFactor)
        result.putLong("modelId", this.modelId)
        return result
      }
  }

  public companion object {
    public fun actionFailureAnalyserToCardRegenerated(
      originalFront: String,
      originalBack: String,
      regenFront: String,
      regenBack: String,
      cardId: Long,
      deckId: Long,
      easeFactor: Int,
      modelId: Long,
    ): NavDirections = ActionFailureAnalyserToCardRegenerated(originalFront, originalBack,
        regenFront, regenBack, cardId, deckId, easeFactor, modelId)
  }
}
