package com.ankiinsight.app.presentation.failure

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmStatic

public data class FailureAnalyserFragmentArgs(
  public val deckId: Long,
  public val deckName: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("deckId", this.deckId)
    result.putString("deckName", this.deckName)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("deckId", this.deckId)
    result.set("deckName", this.deckName)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): FailureAnalyserFragmentArgs {
      bundle.setClassLoader(FailureAnalyserFragmentArgs::class.java.classLoader)
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
      return FailureAnalyserFragmentArgs(__deckId, __deckName)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        FailureAnalyserFragmentArgs {
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
      return FailureAnalyserFragmentArgs(__deckId, __deckName)
    }
  }
}
