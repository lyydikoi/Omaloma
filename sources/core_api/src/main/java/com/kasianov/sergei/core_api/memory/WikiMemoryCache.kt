package com.kasianov.sergei.core_api.memory

import com.kasianov.sergei.core_api.model.dto.WikiArticleDTO
import com.kasianov.sergei.core_api.model.dto.WikiImageInfoDTO

interface WikiMemoryCache {

    fun saveArticle(pageId: String, articles: WikiArticleDTO)

    fun getArticle(pageId: String): WikiArticleDTO?

    fun saveImagesUrs(pageId: String, imagesUrls: List<WikiImageInfoDTO>)

    fun getImagesUrs(pageId: String): List<WikiImageInfoDTO>

    fun saveSearchResult(searchValue: String, pageId: String)

    fun getSearchResult(searchValue: String): String?

    fun clearAllCache()

}