package com.kasianov.sergei.omaloma.domain.model.wikimappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import javax.inject.Inject

class MapperToWikiImagesUrlsList @Inject constructor() :
    Mapper<WikiImageInfoDTO, String> {
    override fun mapDto(input: WikiImageInfoDTO): String {
        return input.url ?: ""
    }
}