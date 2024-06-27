package com.example.odev0.internet.googleSearch.Response

data class Item(
    val displayLink: String,
    val formattedUrl: String,
    val htmlFormattedUrl: String,
    val htmlSnippet: String,
    val htmlTitle: String,
    val kind: String,
    val link: String,
    val pagemap: Pagemap,
    val snippet: String,
    val title: String
)