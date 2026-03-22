package com.ankiinsight.app.data.remote.model

import com.google.gson.annotations.SerializedName

data class GroqRequest(
    @SerializedName("model") val model: String = "llama-3.1-8b-instant",
    @SerializedName("messages") val messages: List<Message>,
    @SerializedName("temperature") val temperature: Double = 0.3,
    @SerializedName("max_tokens") val maxTokens: Int = 800,
    @SerializedName("response_format") val responseFormat: ResponseFormat? = null
)

data class ResponseFormat(
    @SerializedName("type") val type: String = "json_object"
)

data class Message(
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String
)

data class GroqResponse(
    @SerializedName("choices") val choices: List<Choice>?
)

data class Choice(
    @SerializedName("message") val message: Message?
)
