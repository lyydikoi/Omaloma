package com.kasianov.sergei.omaloma.data.memorycache

import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO

interface WikiMemoryCache {

    fun saveArticle(pageId: String, articles: WikiArticleDTO)

    fun getArticle(pageId: String): WikiArticleDTO?

    fun saveImagesUrs(pageId: String, imagesUrls: List<WikiImageInfoDTO>)

    fun getImagesUrs(pageId: String): List<WikiImageInfoDTO>

    fun saveSearchResult(searchValue: String, pageId: String)

    fun getSearchResult(searchValue: String): String?

    fun clearAllCache()

}