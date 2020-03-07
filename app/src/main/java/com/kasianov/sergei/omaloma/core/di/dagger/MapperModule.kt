package com.kasianov.sergei.omaloma.core.di.dagger

import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.network.dto.PublicHolidayDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiArticleDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.model.mappers.*
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

    // PublicHolidays mappers
    @Binds
    abstract fun bindMapperDTOToPublicHoliday(
        mapper: MapperDTOToPublicHoliday
    ): Mapper<PublicHolidayDTO, PublicHoliday>

    // TODO: list mappers are not needed, they are already in Dagger graph?
    /*@Binds
    abstract fun provideListMapperDTOToPublicHoliday(
        listMapper: ListMapperDTOToPublicHoliday
    ): ListMapper<PublicHolidayDTO, PublicHoliday>*/

    @Binds
    abstract fun bindMapperDBToPublicHoliday(
        mapper: MapperDBToPublicHoliday
    ): Mapper<DBPublicHoliday, PublicHoliday>

    /*@Binds
    abstract fun provideListMapperDBToPublicHoliday(
        listMapper: ListMapperDBToPublicHoliday
    ): ListMapper<DBPublicHoliday, PublicHoliday>*/

    @Binds
    abstract fun bindMapperDTOToDBPublicHoliday(
        mapper: MapperDTOToDBPublicHoliday
    ): Mapper<PublicHolidayDTO, DBPublicHoliday>

    /*@Binds
    abstract fun provideListMapperDTOToDBPublicHoliday(
        listMapper: ListMapperDTOToDBPublicHoliday
    ): ListMapper<PublicHolidayDTO, DBPublicHoliday>*/

    // WikiArticle mappers
    @Binds
    abstract fun bindeMapperDTOToWikiArticle(
        mapper: MapperDTOToWikiArticle
    ): Mapper<WikiArticleDTO, WikiArticle>

    @Binds
    abstract fun bindeMapperDTOToWikiImagesUrlsList(
        napper: MapperDTOToWikiImagesUrlsList
    ): Mapper<WikiImageInfoDTO, String>

    /*
    @Binds
    abstract fun provideListMapperDTOToWikiImagesUrlsList(
        listMapper: ListMapperDTOToWikiImagesUrlsList
    ): ListMapper<WikiImageInfoDTO, String>*/

}