package com.kasianov.sergei.omaloma.domain.model.mappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import javax.inject.Inject

class MapperDTOToWikiArticle : Mapper<WikiArticleDTO, WikiArticle> {
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

class MapperDTOToWikiImagesUrlsList : Mapper<WikiImageInfoDTO, String> {
    override fun mapDto(input: WikiImageInfoDTO): String {
        return input.url?.let { it } ?: ""
    }
}

class ListMapperDTOToWikiImagesUrlsList @Inject constructor(
    mapper: MapperDTOToWikiImagesUrlsList
): ListMapperImpl<WikiImageInfoDTO, String>(mapper)