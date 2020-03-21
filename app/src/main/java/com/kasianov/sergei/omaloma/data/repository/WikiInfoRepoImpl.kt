package com.kasianov.sergei.omaloma.data.repository

import com.kasianov.sergei.omaloma.core.di.WIKI_RETROFIT_SERVICE
import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.core.extentions.getRequestResult
import com.kasianov.sergei.omaloma.data.memorycache.WikiMemoryCache
import com.kasianov.sergei.omaloma.data.network.WikiApi
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiPagesResponseDTO
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
    @Named(WIKI_RETROFIT_SERVICE)
    private val wikiApiService: WikiApi,
    private val mapperToWikiArticle: MapperToWikiArticle,
    private val listMapperToWikiImagesUlsList: ListMapper<WikiImageInfoDTO, String>,
    private val wikiMemoryCache: WikiMemoryCache
) : WikiInfoRepo {

    override suspend fun performWikiSearch(searchValue: String): RequestResult<String> {
        wikiMemoryCache.getSearchResult(searchValue)?.let { pageId ->
            return RequestResult.Success(pageId)
        }

        return when (val searchResult = getRequestResult { wikiApiService.getWikiSearchResults(searchValue) }) {

            is RequestResult.Success -> {
                searchResult.data.query?.search?.first()?.pageId?.let { pagId ->
                    wikiMemoryCache.saveSearchResult(searchValue, pagId)
                    RequestResult.Success(pagId)
                } ?: RequestResult.Error(WikiContentException(EMPTY_WIKI_SEARCH_INFO))
            }

            is RequestResult.Error ->
                RequestResult.Error(WikiContentException(EMPTY_WIKI_SEARCH_INFO))
        }
    }

    override suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiArticle> {
        return wikiMemoryCache.getArticle(pageId)?.let {
            val domainModel =  mapperToWikiArticle.mapDto(it)
            RequestResult.Success(domainModel)
        } ?: when(val pageInfoResult = getRequestResult { wikiApiService.getWikiPages(pageId) }) {

            is RequestResult.Success -> {
                extractPageInfoFromResponse(pageInfoResult.data)?.let {
                    wikiMemoryCache.saveArticle(pageId, it)
                    val domainModel =  mapperToWikiArticle.mapDto(it)
                    RequestResult.Success(domainModel)
                } ?: RequestResult.Error(WikiContentException(EMPTY_WIKI_PAGE_CONTENT))
            }

            is RequestResult.Error -> RequestResult.Error(WikiContentException(EMPTY_WIKI_PAGE_CONTENT))
        }
    }

    private fun extractPageInfoFromResponse(response: WikiPagesResponseDTO): WikiArticleDTO? {
        val pageSetsList = response.pagesSet?.pages?.toList()

        return if (pageSetsList.isNullOrEmpty()) {
            null
        } else {
            pageSetsList.first().second
        }
    }

    override suspend fun getWikiImageUrlsList(pageId: String): RequestResult<List<String>> {
        val cachedUrlsList= wikiMemoryCache.getImagesUrs(pageId)

        return if (cachedUrlsList.isNotEmpty()){
            val domainModel = listMapperToWikiImagesUlsList.mapDto(cachedUrlsList)
            RequestResult.Success(domainModel)
        } else {
            when (val urlsListResult = getRequestResult{ wikiApiService.getWikiImages(pageId) }) {

                is RequestResult.Success -> {
                    val urlsList = extractImagesUrlsList(urlsListResult.data)

                    if (urlsList.isEmpty()) {
                        RequestResult.Error(WikiContentException(EMPTY_WIKI_URLS_LIST))
                    } else {
                        wikiMemoryCache.saveImagesUrs(pageId, urlsList)
                        val domainModel = listMapperToWikiImagesUlsList.mapDto(urlsList)
                        RequestResult.Success(domainModel)
                    }
                }

                is RequestResult.Error -> {
                    return RequestResult.Error(WikiContentException(EMPTY_WIKI_URLS_LIST))
                }
            }
        }

    }

    private fun extractImagesUrlsList(data: WikiPagesResponseDTO): List<WikiImageInfoDTO> {
        val urlsList = mutableListOf<WikiImageInfoDTO>()
        val pageSetsList = data.pagesSet?.pages?.toList()

        if (pageSetsList.isNullOrEmpty()) {
            return emptyList()
        }

        pageSetsList.forEach {
            val article: WikiArticleDTO = it.second
            urlsList.add(article.images.first())
        }

        return urlsList
    }

    class WikiContentException(message: String) : Exception(message)

}