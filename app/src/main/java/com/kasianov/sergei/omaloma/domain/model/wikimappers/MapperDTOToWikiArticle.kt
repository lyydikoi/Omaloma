package com.kasianov.sergei.omaloma.domain.model.wikimappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import javax.inject.Inject

class MapperDTOToWikiArticle @Inject constructor() :
    Mapper<WikiArticleDTO, WikiArticle> {
    override fun mapDto(input: WikiArticleDTO): WikiArticle {
        return WikiArticle(
            input.pageId?.let { it } ?: -1,
            input.title?.let { it } ?: "",
            input.description?.let { it } ?: "",
            input.fullUrl?.let { it } ?: "",
            input.extract?.let { it } ?: ""
        )
    }
}