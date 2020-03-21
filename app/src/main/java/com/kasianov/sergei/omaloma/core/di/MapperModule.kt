package com.kasianov.sergei.omaloma.core.di

import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.model.publicholidmappers.*
import com.kasianov.sergei.omaloma.domain.model.wikimappers.ListMapperToWikiImagesUrlsList
import com.kasianov.sergei.omaloma.domain.model.wikimappers.MapperToWikiArticle
import com.kasianov.sergei.omaloma.domain.model.wikimappers.MapperToWikiImagesUrlsList
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

    // PublicHolidays mappers
    @Binds
    abstract fun bindMapperToPublicHoliday(
        mapper: MapperToPublicHoliday
    ): Mapper<PublicHolidayDTO, PublicHoliday>

    @Binds
    abstract fun bindListMapperToPublicHoliday(
        listMapper: ListMapperToPublicHoliday
    ): ListMapper<PublicHolidayDTO, PublicHoliday>

    // WikiArticle mappers
    @Binds
    abstract fun bindMapperToWikiArticle(
        mapper: MapperToWikiArticle
    ): Mapper<WikiArticleDTO, WikiArticle>

    @Binds
    abstract fun bindMapperToWikiImagesUrlsList(
        napper: MapperToWikiImagesUrlsList
    ): Mapper<WikiImageInfoDTO, String>

    @Binds
    abstract fun bindListMapperToWikiImagesUrlsList(
        listMapper: ListMapperToWikiImagesUrlsList
    ): ListMapper<WikiImageInfoDTO, String>

}