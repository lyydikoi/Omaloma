package com.kasianov.sergei.omaloma.data.wikiinfo

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.core.getRequestResult
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiApi
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiPagesResponseDTO
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiSearchResponseDTO
import com.kasianov.sergei.omaloma.domain.WikiInfoRepo

class WikiInfoRepoImpl(private val wikiApiService: WikiApi) : WikiInfoRepo {
    override suspend fun getWikiSearchResult(searchValue: String): RequestResult<WikiSearchResponseDTO> {
        return getRequestResult { wikiApiService.getWikiSearchResults(searchValue) }
    }

    override suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiPagesResponseDTO> {
        return getRequestResult { wikiApiService.getWikiPages(pageId) }
    }

    override suspend fun getWikiImageUrlsList(pageId: String): RequestResult<WikiPagesResponseDTO> {
        return getRequestResult { wikiApiService.getWikiImages(pageId) }
    }

}