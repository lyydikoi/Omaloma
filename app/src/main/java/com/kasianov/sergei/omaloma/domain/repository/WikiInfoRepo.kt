package com.kasianov.sergei.omaloma.domain.repository

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.WikiArticle

interface WikiInfoRepo {

    suspend fun performWikiSearch(searchValue: String): RequestResult<String>

    suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiArticle>

    suspend fun getWikiImageUrlsList(pageId: String): RequestResult<List<String>>

}