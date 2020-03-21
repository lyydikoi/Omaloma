package com.kasianov.sergei.omaloma.domain.usecases.wikiusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.WikiArticle

interface GetWikiPageInfoUseCase {
    suspend operator fun invoke(pageId: String): RequestResult<WikiArticle>
}