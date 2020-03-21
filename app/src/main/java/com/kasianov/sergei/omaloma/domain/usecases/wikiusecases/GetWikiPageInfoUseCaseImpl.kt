package com.kasianov.sergei.omaloma.domain.usecases.wikiusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import javax.inject.Inject

class GetWikiPageInfoUseCaseImpl  @Inject constructor(
    private val wikiInfoRepo: WikiInfoRepo
) : GetWikiPageInfoUseCase {
    override suspend fun invoke(pageId: String): RequestResult<WikiArticle> {
        return wikiInfoRepo.getWikiPageInfo(pageId)
    }
}