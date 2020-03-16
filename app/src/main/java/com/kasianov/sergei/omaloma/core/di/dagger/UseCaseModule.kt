package com.kasianov.sergei.omaloma.core.di.dagger
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetAllPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetAllPublicHolidaysUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetPublicHolidayUseCase
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetPublicHolidayUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.wikiusecases.PerformWikiSearchUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.wikiusecases.PerformWikiSearchUseCase
import com.kasianov.sergei.omaloma.domain.usecases.wikiusecases.GetWikiPageInfoUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.wikiusecases.GetWikiPageInfoUseCase
import com.kasianov.sergei.omaloma.domain.usecases.wikiusecases.GetWikiUrlsListUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.wikiusecases.GetWikiUrlsListUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    // Public holidays use cases
    @Binds
    abstract fun bindGetAllPublicHolidaysUseCase(
        useCase: GetAllPublicHolidaysUseCaseImpl
    ): GetAllPublicHolidaysUseCase


    @Binds
    abstract fun bindGetStoPublicHolidayUseCase(
        useCase: GetPublicHolidayUseCaseImpl
    ): GetPublicHolidayUseCase


    // Wikipedia use cases
    @Binds
    abstract fun bindPerformWikiSearchUseCase(
        useCase: PerformWikiSearchUseCaseImpl
    ): PerformWikiSearchUseCase

    @Binds
    abstract fun bindGetWikiPageInfoUseCase(
        useCase: GetWikiPageInfoUseCaseImpl
    ): GetWikiPageInfoUseCase

    @Binds
    abstract fun bindGetWikiUrlsListUseCase(
        useCase: GetWikiUrlsListUseCaseImpl
    ): GetWikiUrlsListUseCase

}