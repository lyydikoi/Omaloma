package com.kasianov.sergei.core_api.repository

import com.kasianov.sergei.core_api.extentions.RequestResult
import com.kasianov.sergei.core_api.model.dto.WikiArticleDTO

interface WikiInfoRepo {

    suspend fun performWikiSearch(searchValue: String): RequestResult<String>

    suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiArticleDTO>

    suspend fun getWikiImageUrlsList(pageId: String): RequestResult<List<String>>
}
