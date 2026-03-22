package com.ankiinsight.app.presentation.gap

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmStatic

public data class ConceptGapFragmentArgs(
  public val deckId: Long,
  public val deckName: String,
  public val failedCount: Int,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("deckId", this.deckId)
    result.putString("deckName", this.deckName)
    result.putInt("failedCount", this.failedCount)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("deckId", this.deckId)
    result.set("deckName", this.deckName)
    result.set("failedCount", this.failedCount)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ConceptGapFragmentArgs {
      bundle.setClassLoader(ConceptGapFragmentArgs::class.java.classLoader)
      val __deckId : Long
      if (bundle.containsKey("deckId")) {
        __deckId = bundle.getLong("deckId")
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      val __deckName : String?
      if (bundle.containsKey("deckName")) {
        __deckName = bundle.getString("deckName")
        if (__deckName == null) {
          throw IllegalArgumentException("Argument \"deckName\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckName\" is missing and does not have an android:defaultValue")
      }
      val __failedCount : Int
      if (bundle.containsKey("failedCount")) {
        __failedCount = bundle.getInt("failedCount")
      } else {
        throw IllegalArgumentException("Required argument \"failedCount\" is missing and does not have an android:defaultValue")
      }
      return ConceptGapFragmentArgs(__deckId, __deckName, __failedCount)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): ConceptGapFragmentArgs {
      val __deckId : Long?
      if (savedStateHandle.contains("deckId")) {
        __deckId = savedStateHandle["deckId"]
        if (__deckId == null) {
          throw IllegalArgumentException("Argument \"deckId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      val __deckName : String?
      if (savedStateHandle.contains("deckName")) {
        __deckName = savedStateHandle["deckName"]
        if (__deckName == null) {
          throw IllegalArgumentException("Argument \"deckName\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckName\" is missing and does not have an android:defaultValue")
      }
      val __failedCount : Int?
      if (savedStateHandle.contains("failedCount")) {
        __failedCount = savedStateHandle["failedCount"]
        if (__failedCount == null) {
          throw IllegalArgumentException("Argument \"failedCount\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"failedCount\" is missing and does not have an android:defaultValue")
      }
      return ConceptGapFragmentArgs(__deckId, __deckName, __failedCount)
    }
  }
}
