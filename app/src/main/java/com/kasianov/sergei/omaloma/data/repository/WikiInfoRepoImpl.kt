package com.kasianov.sergei.omaloma.data.repository

import com.kasianov.sergei.omaloma.core.di.dagger.WIKI_RETROFIT_SERVICE
import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.core.extentions.getRequestResult
import com.kasianov.sergei.omaloma.data.network.WikiApi
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.model.wikimappers.MapperToWikiArticle
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

const val EMPTY_WIKI_PAGE_CONTENT = "empty_wiki_page_content"
const val EMPTY_WIKI_URLS_LIST = "empty_wiki_urls_list"
const val EMPTY_WIKI_SEARCH_INFO = "empty_wiki_search_info"

class WikiInfoRepoImpl @Inject constructor(
    @Named(WIKI_RETROFIT_SERVICE) private val wikiApiService: WikiApi,
    private val mapperDTOToWikiArticle: MapperToWikiArticle,
    private val listMapperDTOToWikiImagesUlsList: ListMapper<WikiImageInfoDTO, String>
) : WikiInfoRepo {
    override suspend fun performWikiSearch(searchValue: String): RequestResult<String> {
        return when (val searchResult = getRequestResult { wikiApiService.getWikiSearchResults(searchValue) }) {
            is RequestResult.Success -> {
                searchResult.data.query?.search?.first()?.pageId?.let {
                    RequestResult.Success(it)
                } ?: RequestResult.Error(WikiContentException(EMPTY_WIKI_SEARCH_INFO))
            }
            is RequestResult.Error -> searchResult
        }
    }

    override suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiArticle> {
        return when(val pageInfoResult = getRequestResult { wikiApiService.getWikiPages(pageId) }) {
            is RequestResult.Success -> {
                val pageSetsList = pageInfoResult.data.pagesSet?.pages?.toList()
                if (pageSetsList.isNullOrEmpty()) {
                    return RequestResult.Error(WikiContentException(EMPTY_WIKI_PAGE_CONTENT))
                }
                return RequestResult.Success(
                    mapperDTOToWikiArticle.mapDto(pageSetsList.first().second)
                )
            }
            is RequestResult.Error -> pageInfoResult
        }
    }

    override suspend fun getWikiImageUrlsList(pageId: String): RequestResult<List<String>> {
        return when (val urlsListResult = getRequestResult{ wikiApiService.getWikiImages(pageId) }) {
            is RequestResult.Success -> {
                val pageSetsList = urlsListResult.data.pagesSet?.pages?.toList()
                val urlsList = mutableListOf<WikiImageInfoDTO>()
                if (pageSetsList.isNullOrEmpty()) {
                    return RequestResult.Error(WikiContentException(EMPTY_WIKI_URLS_LIST))
                }
                pageSetsList.forEach {
                    val article: WikiArticleDTO = it.second
                    urlsList.add(article.images.first())
                }
                return RequestResult.Success(
                    listMapperDTOToWikiImagesUlsList.mapDto(urlsList)
                )
            }
            is RequestResult.Error -> urlsListResult
        }
    }

    class WikiContentException(message: String) : Exception(message)

}