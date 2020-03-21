package com.kasianov.sergei.omaloma.domain.usecases.wikiusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult

interface GetWikiUrlsListUseCase {
    suspend operator fun invoke(pageId: String): RequestResult<List<String>>
}