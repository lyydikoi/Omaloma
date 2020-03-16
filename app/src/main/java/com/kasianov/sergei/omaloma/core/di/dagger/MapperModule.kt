package com.kasianov.sergei.omaloma.core.di.dagger

import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.model.publicholidmappers.*
import com.kasianov.sergei.omaloma.domain.model.wikimappers.ListMapperDTOToWikiImagesUrlsList
import com.kasianov.sergei.omaloma.domain.model.wikimappers.MapperDTOToWikiArticle
import com.kasianov.sergei.omaloma.domain.model.wikimappers.MapperDTOToWikiImagesUrlsList
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

    // PublicHolidays mappers
    @Binds
    abstract fun bindMapperDTOToPublicHoliday(
        mapper: MapperDTOToPublicHoliday
    ): Mapper<PublicHolidayDTO, PublicHoliday>

    @Binds
    abstract fun bindListMapperDTOToPublicHoliday(
        listMapper: ListMapperDTOToPublicHoliday
    ): ListMapper<PublicHolidayDTO, PublicHoliday>

    // WikiArticle mappers
    @Binds
    abstract fun bindMapperDTOToWikiArticle(
        mapper: MapperDTOToWikiArticle
    ): Mapper<WikiArticleDTO, WikiArticle>

    @Binds
    abstract fun bindMapperDTOToWikiImagesUrlsList(
        napper: MapperDTOToWikiImagesUrlsList
    ): Mapper<WikiImageInfoDTO, String>

    @Binds
    abstract fun bindListMapperDTOToWikiImagesUrlsList(
        listMapper: ListMapperDTOToWikiImagesUrlsList
    ): ListMapper<WikiImageInfoDTO, String>

}