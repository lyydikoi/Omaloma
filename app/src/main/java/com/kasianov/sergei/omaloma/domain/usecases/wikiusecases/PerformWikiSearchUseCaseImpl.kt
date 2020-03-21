package com.kasianov.sergei.omaloma.domain.usecases.wikiusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import javax.inject.Inject

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