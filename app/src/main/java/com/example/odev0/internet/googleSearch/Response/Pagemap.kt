package com.example.odev0.internet.googleSearch.Response

data class Pagemap(
    val BreadcrumbList: List<Breadcrumb>,
    val SocialMediaPosting: List<SocialMediaPosting>,
    val cse_image: List<Cseİmage>,
    val cse_thumbnail: List<CseThumbnail>,
    val hcard: List<Hcard>,
    val xfn: List<Xfn>
)