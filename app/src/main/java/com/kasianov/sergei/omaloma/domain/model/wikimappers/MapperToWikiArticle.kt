package com.kasianov.sergei.omaloma.domain.model.wikimappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import javax.inject.Inject

class MapperToWikiArticle @Inject constructor() :
    Mapper<WikiArticleDTO, WikiArticle> {
    override fun mapDto(input: WikiArticleDTO): WikiArticle {
        return WikiArticle(
            input.pageId ?: -1,
            input.title ?: "",
            input.description ?: "",
            input.fullUrl ?: "",
            input.extract ?: ""
        )
    }
}