package com.kasianov.sergei.omaloma.data.memorycache

import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import java.util.*
import javax.inject.Inject

class WikiMemoryCacheImpl @Inject constructor() : WikiMemoryCache {

    private val articleStorage = Collections.synchronizedMap(mutableMapOf<String, WikiArticleDTO>())
    private val imageUrlsStorage = Collections.synchronizedMap(mutableMapOf<String, List<WikiImageInfoDTO>>())
    private val searchResultStorage = Collections.synchronizedMap(mutableMapOf<String, String>())

    override fun saveArticle(pageId: String, article: WikiArticleDTO) {
        articleStorage[pageId] = article
    }

    override fun getArticle(pageId: String): WikiArticleDTO? = articleStorage[pageId]

    override fun saveImagesUrs(pageId: String, imagesUrls: List<WikiImageInfoDTO>) {
        imageUrlsStorage[pageId] = imagesUrls
    }

    override fun getImagesUrs(pageId: String): List<WikiImageInfoDTO>
            = imageUrlsStorage[pageId] ?: emptyList()

    override fun saveSearchResult(searchValue: String, pageId: String) {
        searchResultStorage[searchValue] = pageId
    }

    override fun getSearchResult(searchValue: String): String? = searchResultStorage[searchValue]

    override fun clearAllCache() {
        articleStorage.clear()
        imageUrlsStorage.clear()
        searchResultStorage.clear()
    }

}