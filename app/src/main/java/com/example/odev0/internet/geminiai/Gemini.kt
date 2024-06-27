package com.example.odev0.internet.geminiai

import com.example.odev0.BuildConfig
import com.example.odev0.models.Message
import com.google.ai.client.generativeai.GenerativeModel

object Gemini {
    private val gemini = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY,
    )

    suspend fun GetResponse(prompt:String): Message?{
        try {
            val response = gemini.generateContent(prompt = prompt)
            return Message(false,response.text.toString())
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

}