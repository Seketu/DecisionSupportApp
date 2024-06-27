package com.example.odev0.internet.googleSearch.Response

data class SearchInformation(
    val formattedSearchTime: String,
    val formattedTotalResults: String,
    val searchTime: Double,
    val totalResults: String
)