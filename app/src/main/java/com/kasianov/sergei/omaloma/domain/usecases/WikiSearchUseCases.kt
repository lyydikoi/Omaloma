package com.kasianov.sergei.omaloma.domain.usecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import javax.inject.Inject

// Interfaces
interface PerformWikiSearchUseCase {
    suspend operator fun invoke(searchValue: String): RequestResult<WikiArticle>
}

interface GetWikiPageInfoUseCase {
    suspend operator fun invoke(pageId: String): RequestResult<WikiArticle>
}

interface GetWikiUrlsListUseCase {
    suspend operator fun invoke(pageId: String): RequestResult<List<String>>
}

// Implementations
class PerformWikiSearchUseCaseImpl @Inject constructor(
    private val wikiInfoRepo: WikiInfoRepo,
    private val getWikiPageInfoUseCase: GetWikiPageInfoUseCase
) : PerformWikiSearchUseCase {
    override suspend fun invoke(searchValue: String): RequestResult<WikiArticle> {
        return when (val searchResult = wikiInfoRepo.performWikiSearch(searchValue)) {
            is RequestResult.Success -> getWikiPageInfoUseCase(searchResult.data)
            is RequestResult.Error -> searchResult
        }

    }
}

class GetWikiPageInfoUseCaseImpl  @Inject constructor(
    private val wikiInfoRepo: WikiInfoRepo
) : GetWikiPageInfoUseCase {
    override suspend fun invoke(pageId: String): RequestResult<WikiArticle> {
        return wikiInfoRepo.getWikiPageInfo(pageId)
    }
}

class GetWikiUrlsListUseCaseImpl  @Inject constructor(
    private val wikiInfoRepo: WikiInfoRepo
) : GetWikiUrlsListUseCase {
    override suspend fun invoke(pageId:String): RequestResult<List<String>> {
        return wikiInfoRepo.getWikiImageUrlsList(pageId)
    }
}