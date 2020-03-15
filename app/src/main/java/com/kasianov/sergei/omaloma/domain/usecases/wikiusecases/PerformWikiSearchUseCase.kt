package com.kasianov.sergei.omaloma.domain.usecases.wikiusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.WikiArticle

// Interfaces
interface PerformWikiSearchUseCase {
    suspend operator fun invoke(searchValue: String): RequestResult<WikiArticle>
}