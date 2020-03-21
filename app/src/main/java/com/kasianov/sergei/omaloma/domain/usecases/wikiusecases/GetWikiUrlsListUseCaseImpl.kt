package com.kasianov.sergei.omaloma.domain.usecases.wikiusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import javax.inject.Inject

class GetWikiUrlsListUseCaseImpl  @Inject constructor(
    private val wikiInfoRepo: WikiInfoRepo
) : GetWikiUrlsListUseCase {
    override suspend fun invoke(pageId:String): RequestResult<List<String>> {
        return wikiInfoRepo.getWikiImageUrlsList(pageId)
    }
}