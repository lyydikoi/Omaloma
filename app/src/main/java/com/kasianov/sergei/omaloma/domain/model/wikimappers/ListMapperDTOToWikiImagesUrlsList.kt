package com.kasianov.sergei.omaloma.domain.model.wikimappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import javax.inject.Inject

class ListMapperDTOToWikiImagesUrlsList @Inject constructor(
    mapper: MapperDTOToWikiImagesUrlsList
): ListMapperImpl<WikiImageInfoDTO, String>(mapper)