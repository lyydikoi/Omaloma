package com.kasianov.sergei.omaloma.domain.model.wikimappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import javax.inject.Inject

class ListMapperToWikiImagesUrlsList @Inject constructor(
    mapper: MapperToWikiImagesUrlsList
): ListMapperImpl<WikiImageInfoDTO, String>(mapper)