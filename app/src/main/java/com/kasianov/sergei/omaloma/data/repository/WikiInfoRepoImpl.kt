package com.kasianov.sergei.omaloma.data.repository

import com.kasianov.sergei.omaloma.core.di.dagger.WIKI_RETROFIT_SERVICE
import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.core.extentions.getRequestResult
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
import javax.inject.Singleton

const val EMPTY_WIKI_PAGE_CONTENT = "empty_wiki_page_content"
const val EMPTY_WIKI_URLS_LIST = "empty_wiki_urls_list"
const val EMPTY_WIKI_SEARCH_INFO = "empty_wiki_search_info"

@Singleton
class WikiInfoRepoImpl @Inject constructor(
    @Named(WIKI_RETROFIT_SERVICE) private val wikiApiService: WikiApi,
    private val mapperToWikiArticle: MapperToWikiArticle,
    private val listMapperToWikiImagesUlsList: ListMapper<WikiImageInfoDTO, String>
) : WikiInfoRepo {

    private val articleCache = hashMapOf<String, WikiArticleDTO>()
    private val imagesUrlsCache = hashMapOf<String, List<WikiImageInfoDTO>>()
    private val searchCache = hashMapOf<String, String>()

    override suspend fun performWikiSearch(searchValue: String): RequestResult<String> {

        return if (searchCache.contains(searchValue)) {
            RequestResult.Success(searchCache[searchValue]!!)
        } else {
            when (val searchResult = getRequestResult { wikiApiService.getWikiSearchResults(searchValue) }) {
                is RequestResult.Success -> {
                    searchResult.data.query?.search?.first()?.pageId?.let {
                        searchCache[searchValue] = it
                        RequestResult.Success(it)
                    } ?: RequestResult.Error(WikiContentException(EMPTY_WIKI_SEARCH_INFO))
                }
                is RequestResult.Error -> searchResult
            }
        }
    }

    override suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiArticle> {

        return if (articleCache[pageId] != null) {
            RequestResult.Success(mapperToWikiArticle.mapDto(articleCache[pageId]!!))
        } else {
            when(val pageInfoResult = getRequestResult { wikiApiService.getWikiPages(pageId) }) {
                is RequestResult.Success -> {
                    extractPageInfoFromResponse(pageInfoResult.data)?.let { wikiArticleDTO ->
                        articleCache[pageId] = wikiArticleDTO
                        RequestResult.Success(mapperToWikiArticle.mapDto(wikiArticleDTO))
                    } ?: RequestResult.Error(WikiContentException(EMPTY_WIKI_PAGE_CONTENT))
                }
                is RequestResult.Error -> pageInfoResult
            }
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
        return if (imagesUrlsCache.containsKey(pageId)) {
            RequestResult.Success(listMapperToWikiImagesUlsList.mapDto(imagesUrlsCache[pageId]!!))
        } else {
            when (val urlsListResult = getRequestResult{ wikiApiService.getWikiImages(pageId) }) {
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
                    imagesUrlsCache[pageId] = urlsList
                    RequestResult.Success(listMapperToWikiImagesUlsList.mapDto(urlsList))
                }
                is RequestResult.Error -> urlsListResult
            }
        }
    }

    class WikiContentException(message: String) : Exception(message)

}