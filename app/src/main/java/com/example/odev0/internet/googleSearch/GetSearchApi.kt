package com.example.odev0.internet.googleSearch

import com.example.odev0.BuildConfig
import com.example.odev0.internet.googleSearch.Response.SearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GetSearchApi {

    @GET("/customsearch/v1")
    suspend fun getSearch(
        @Query("key") apiKey :String = BuildConfig.SEARCH_API_KEY,
        @Query("q") query : String,
        @Query("cx") cx : String = "f3959e191c7b04045"
    ) : Response<SearchResponse>
}