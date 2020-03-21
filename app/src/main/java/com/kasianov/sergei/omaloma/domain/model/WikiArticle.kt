package com.kasianov.sergei.omaloma.domain.model

data class WikiArticle(
    val pageId: Int,
    val title: String,
    val description: String,
    val fullUrl: String,
    val extract: String,
    val urlsList: MutableList<String> = mutableListOf()
)