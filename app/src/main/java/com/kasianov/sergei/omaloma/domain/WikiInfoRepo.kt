package com.kasianov.sergei.omaloma.domain

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiPagesResponseDTO
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiSearchResponseDTO

interface WikiInfoRepo {
    suspend fun getWikiSearchResult(searchValue: String): RequestResult<WikiSearchResponseDTO>

    suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiPagesResponseDTO>

    suspend fun getWikiImageUrlsList(pageId: String): RequestResult<WikiPagesResponseDTO>

}