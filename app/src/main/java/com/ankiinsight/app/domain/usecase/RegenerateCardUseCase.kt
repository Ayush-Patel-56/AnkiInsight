package com.ankiinsight.app.domain.usecase

import android.util.Log
import com.ankiinsight.app.data.remote.GroqApiService
import com.ankiinsight.app.domain.model.CardData
import com.google.gson.Gson
import javax.inject.Inject

data class RegeneratedCard(val front: String, val back: String)

class RegenerateCardUseCase @Inject constructor(
    private val groqApiService: GroqApiService
) {
    private val gson = Gson()

    suspend operator fun invoke(card: CardData): RegeneratedCard {
        val system = """You are an Anki flashcard expert. Rewrite poorly retained cards to be clearer, 
use analogies, and be more memorable. Return ONLY valid JSON with 'front' and 'back' fields."""
        val user = """This flashcard is being repeatedly forgotten.
Front: ${card.front}
Back: ${card.back}
Rewrite it using a simpler explanation or analogy."""

        val raw = groqApiService.complete(system, user)
        if (raw == "API_KEY_MISSING") throw Exception("GROQ_API_KEY missing. Gradle Sync required!")
        if (raw == "RATE_LIMITED") throw Exception("Groq API rate limit exceeded.")
        if (raw.isBlank() || raw.startsWith("HTTP_ERROR:")) throw Exception("Network/API Error: $raw")

        try {
            val start = raw.indexOf('{')
            val end = raw.lastIndexOf('}')
            if (start == -1 || end == -1) throw Exception("LLM did not return JSON format.")
            val json = raw.substring(start, end + 1)
            val map = gson.fromJson(json, Map::class.java)
            val front = map["front"]?.toString() ?: throw Exception("Missing 'front' in JSON")
            val back = map["back"]?.toString() ?: throw Exception("Missing 'back' in JSON")
            return RegeneratedCard(front, back)
        } catch (e: Exception) {
            Log.e("RegenerateCardUseCase", "Parse error: ${e.message} on output: $raw")
            throw Exception("Parse error: ${e.message}")
        }
    }
}
