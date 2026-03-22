package com.ankiinsight.app.presentation.regenerated

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CardRegeneratedFragmentArgs(
  public val originalFront: String,
  public val originalBack: String,
  public val regenFront: String,
  public val regenBack: String,
  public val cardId: Long,
  public val deckId: Long,
  public val easeFactor: Int,
  public val modelId: Long,
) : NavArgs {
  public fun toBundle(): Bundle {
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

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("originalFront", this.originalFront)
    result.set("originalBack", this.originalBack)
    result.set("regenFront", this.regenFront)
    result.set("regenBack", this.regenBack)
    result.set("cardId", this.cardId)
    result.set("deckId", this.deckId)
    result.set("easeFactor", this.easeFactor)
    result.set("modelId", this.modelId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CardRegeneratedFragmentArgs {
      bundle.setClassLoader(CardRegeneratedFragmentArgs::class.java.classLoader)
      val __originalFront : String?
      if (bundle.containsKey("originalFront")) {
        __originalFront = bundle.getString("originalFront")
        if (__originalFront == null) {
          throw IllegalArgumentException("Argument \"originalFront\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"originalFront\" is missing and does not have an android:defaultValue")
      }
      val __originalBack : String?
      if (bundle.containsKey("originalBack")) {
        __originalBack = bundle.getString("originalBack")
        if (__originalBack == null) {
          throw IllegalArgumentException("Argument \"originalBack\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"originalBack\" is missing and does not have an android:defaultValue")
      }
      val __regenFront : String?
      if (bundle.containsKey("regenFront")) {
        __regenFront = bundle.getString("regenFront")
        if (__regenFront == null) {
          throw IllegalArgumentException("Argument \"regenFront\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"regenFront\" is missing and does not have an android:defaultValue")
      }
      val __regenBack : String?
      if (bundle.containsKey("regenBack")) {
        __regenBack = bundle.getString("regenBack")
        if (__regenBack == null) {
          throw IllegalArgumentException("Argument \"regenBack\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"regenBack\" is missing and does not have an android:defaultValue")
      }
      val __cardId : Long
      if (bundle.containsKey("cardId")) {
        __cardId = bundle.getLong("cardId")
      } else {
        throw IllegalArgumentException("Required argument \"cardId\" is missing and does not have an android:defaultValue")
      }
      val __deckId : Long
      if (bundle.containsKey("deckId")) {
        __deckId = bundle.getLong("deckId")
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      val __easeFactor : Int
      if (bundle.containsKey("easeFactor")) {
        __easeFactor = bundle.getInt("easeFactor")
      } else {
        throw IllegalArgumentException("Required argument \"easeFactor\" is missing and does not have an android:defaultValue")
      }
      val __modelId : Long
      if (bundle.containsKey("modelId")) {
        __modelId = bundle.getLong("modelId")
      } else {
        throw IllegalArgumentException("Required argument \"modelId\" is missing and does not have an android:defaultValue")
      }
      return CardRegeneratedFragmentArgs(__originalFront, __originalBack, __regenFront, __regenBack,
          __cardId, __deckId, __easeFactor, __modelId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        CardRegeneratedFragmentArgs {
      val __originalFront : String?
      if (savedStateHandle.contains("originalFront")) {
        __originalFront = savedStateHandle["originalFront"]
        if (__originalFront == null) {
          throw IllegalArgumentException("Argument \"originalFront\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"originalFront\" is missing and does not have an android:defaultValue")
      }
      val __originalBack : String?
      if (savedStateHandle.contains("originalBack")) {
        __originalBack = savedStateHandle["originalBack"]
        if (__originalBack == null) {
          throw IllegalArgumentException("Argument \"originalBack\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"originalBack\" is missing and does not have an android:defaultValue")
      }
      val __regenFront : String?
      if (savedStateHandle.contains("regenFront")) {
        __regenFront = savedStateHandle["regenFront"]
        if (__regenFront == null) {
          throw IllegalArgumentException("Argument \"regenFront\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"regenFront\" is missing and does not have an android:defaultValue")
      }
      val __regenBack : String?
      if (savedStateHandle.contains("regenBack")) {
        __regenBack = savedStateHandle["regenBack"]
        if (__regenBack == null) {
          throw IllegalArgumentException("Argument \"regenBack\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"regenBack\" is missing and does not have an android:defaultValue")
      }
      val __cardId : Long?
      if (savedStateHandle.contains("cardId")) {
        __cardId = savedStateHandle["cardId"]
        if (__cardId == null) {
          throw IllegalArgumentException("Argument \"cardId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"cardId\" is missing and does not have an android:defaultValue")
      }
      val __deckId : Long?
      if (savedStateHandle.contains("deckId")) {
        __deckId = savedStateHandle["deckId"]
        if (__deckId == null) {
          throw IllegalArgumentException("Argument \"deckId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      val __easeFactor : Int?
      if (savedStateHandle.contains("easeFactor")) {
        __easeFactor = savedStateHandle["easeFactor"]
        if (__easeFactor == null) {
          throw IllegalArgumentException("Argument \"easeFactor\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"easeFactor\" is missing and does not have an android:defaultValue")
      }
      val __modelId : Long?
      if (savedStateHandle.contains("modelId")) {
        __modelId = savedStateHandle["modelId"]
        if (__modelId == null) {
          throw IllegalArgumentException("Argument \"modelId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"modelId\" is missing and does not have an android:defaultValue")
      }
      return CardRegeneratedFragmentArgs(__originalFront, __originalBack, __regenFront, __regenBack,
          __cardId, __deckId, __easeFactor, __modelId)
    }
  }
}
