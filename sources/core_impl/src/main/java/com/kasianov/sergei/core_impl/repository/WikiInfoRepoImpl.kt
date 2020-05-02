package com.kasianov.sergei.core_impl.repository

import com.kasianov.sergei.core_api.di_utils.WikiRetrofitService
import com.kasianov.sergei.core_api.model.dto.WikiArticleDTO
import com.kasianov.sergei.core_api.model.dto.WikiImageInfoDTO
import com.kasianov.sergei.core_api.model.dto.WikiPagesResponseDTO
import com.kasianov.sergei.core_api.extentions.RequestResult
import com.kasianov.sergei.core_api.extentions.getRequestResult
import com.kasianov.sergei.core_api.memory.WikiMemoryCache
import com.kasianov.sergei.core_api.network.WikiApi
import com.kasianov.sergei.core_api.repository.WikiInfoRepo
import java.lang.Exception
import javax.inject.Inject

const val EMPTY_WIKI_PAGE_CONTENT = "empty_wiki_page_content"
const val EMPTY_WIKI_URLS_LIST = "empty_wiki_urls_list"
const val EMPTY_WIKI_SEARCH_INFO = "empty_wiki_search_info"

class WikiInfoRepoImpl @Inject constructor(
    @WikiRetrofitService private val wikiApiService: WikiApi,
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
                } ?: RequestResult.Error(
                    WikiContentException(
                        EMPTY_WIKI_SEARCH_INFO
                    )
                )
            }

            is RequestResult.Error -> RequestResult.Error(
                WikiContentException(
                    EMPTY_WIKI_SEARCH_INFO
                )
            )
        }
    }

    override suspend fun getWikiPageInfo(pageId: String): RequestResult<WikiArticleDTO> {
        wikiMemoryCache.getArticle(pageId)?.let { article ->
            return  RequestResult.Success(article)
        }

        return when(val pageInfoResult = getRequestResult { wikiApiService.getWikiPages(pageId) }) {
            is RequestResult.Success -> {
                extractPageInfoFromResponse(pageInfoResult.data)?.let { article ->
                    wikiMemoryCache.saveArticle(pageId, article)
                    RequestResult.Success(article)
                } ?: RequestResult.Error(
                    WikiContentException(
                        EMPTY_WIKI_PAGE_CONTENT
                    )
                )
            }

            is RequestResult.Error -> RequestResult.Error(
                WikiContentException(
                    EMPTY_WIKI_PAGE_CONTENT
                )
            )
        }
    }

    private fun extractPageInfoFromResponse(response: WikiPagesResponseDTO): WikiArticleDTO? {
        val pageSetsList = response.pagesSet?.pages?.toList()

        return if (pageSetsList.isNullOrEmpty()) null
        else pageSetsList.first().second
    }

    override suspend fun getWikiImageUrlsList(pageId: String): RequestResult<List<String>> {
        val cachedUrlsList: List<WikiImageInfoDTO> = wikiMemoryCache.getImagesUrs(pageId)

        if (cachedUrlsList.isNotEmpty()){
            return RequestResult.Success(cachedUrlsList.map { it.url ?: "" })
        }

        return when (val urlsListResult = getRequestResult { wikiApiService.getWikiImages(pageId) }) {
            is RequestResult.Success -> {
                val urlsList = extractImagesUrlsList(urlsListResult.data)

                if (urlsList.isEmpty()) {
                    RequestResult.Error(WikiContentException(EMPTY_WIKI_URLS_LIST))
                } else {
                    wikiMemoryCache.saveImagesUrs(pageId, urlsList)
                    RequestResult.Success(urlsList.map { it.url ?: "" })
                }
            }

            is RequestResult.Error -> { return RequestResult.Error(WikiContentException(EMPTY_WIKI_URLS_LIST)) }
        }
    }

    private fun extractImagesUrlsList(data: WikiPagesResponseDTO): List<WikiImageInfoDTO> {
        val urlsList = mutableListOf<WikiImageInfoDTO>()
        val pageSetsList = data.pagesSet?.pages?.toList()

        if (pageSetsList.isNullOrEmpty()) return emptyList()

        pageSetsList.forEach {
            val article: WikiArticleDTO = it.second
            urlsList.add(article.images.first())
        }

        return urlsList
    }

    class WikiContentException(message: String) : Exception(message)

}