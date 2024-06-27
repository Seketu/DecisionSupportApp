package com.example.odev0.internet.googleSearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object searchApi {
    private val _searchApi = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getSearchApi(): GetSearchApi{
        return _searchApi.create(GetSearchApi :: class.java)
    }
}