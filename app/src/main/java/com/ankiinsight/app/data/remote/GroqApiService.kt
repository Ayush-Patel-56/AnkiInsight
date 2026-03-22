package com.ankiinsight.app.data.remote

import android.util.Log
import com.ankiinsight.app.BuildConfig
import com.ankiinsight.app.data.remote.model.GroqRequest
import com.ankiinsight.app.data.remote.model.GroqResponse
import com.ankiinsight.app.data.remote.model.Message
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class GroqApiService @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    companion object {
        private const val TAG = "GroqApiService"
        private const val BASE_URL = "https://api.groq.com/openai/v1/chat/completions"
        private const val MODEL = "llama-3.1-8b-instant"
        private val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    }

    private val gson = Gson()

    suspend fun complete(systemPrompt: String, userMessage: String): String =
        withContext(Dispatchers.IO) {
            try {
                val apiKey = BuildConfig.GROQ_API_KEY
                if (apiKey.isNullOrBlank()) {
                    Log.e(TAG, "GROQ_API_KEY is missing. Add groq.api.key to local.properties")
                    return@withContext "API_KEY_MISSING"
                }

                val requestBody = GroqRequest(
                    model = MODEL,
                    messages = listOf(
                        Message(role = "system", content = systemPrompt),
                        Message(role = "user", content = userMessage)
                    ),
                    temperature = 0.3,
                    maxTokens = 800,
                    responseFormat = com.ankiinsight.app.data.remote.model.ResponseFormat()
                )

                val json = gson.toJson(requestBody)
                val body = json.toRequestBody(MEDIA_TYPE_JSON)

                val request = Request.Builder()
                    .url(BASE_URL)
                    .addHeader("Authorization", "Bearer $apiKey")
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build()

                val response = okHttpClient.newCall(request).execute()

                if (!response.isSuccessful) {
                    val code = response.code
                    val errorBody = response.body?.string() ?: ""
                    return@withContext if (code == 429) "RATE_LIMITED" else "HTTP_ERROR: $code - $errorBody"
                }

                val responseBody = response.body?.string() ?: return@withContext ""
                val groqResponse = gson.fromJson(responseBody, GroqResponse::class.java)
                groqResponse.choices?.firstOrNull()?.message?.content ?: ""
            } catch (e: Exception) {
                Log.e(TAG, "GroqApiService.complete error: ${e.message}")
                ""
            }
        }
}
