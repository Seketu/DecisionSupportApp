package com.example.odev0.internet.googleSearch.Response

data class SearchResponse(
    val context: Context,
    val items: List<Item>,
    val kind: String,
    val queries: Queries,
    val searchInformation: SearchInformation,
    val url: Url
)