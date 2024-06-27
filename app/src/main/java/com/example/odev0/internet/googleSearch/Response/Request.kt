package com.example.odev0.internet.googleSearch.Response

data class Request(
    val count: Int,
    val cx: String,
    val inputEncoding: String,
    val outputEncoding: String,
    val safe: String,
    val searchTerms: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)