package com.kasianov.sergei.omaloma.core.di.dagger
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetAllStoredPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetAllStoredPublicHolidaysUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.LoadPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.LoadPublicHolidaysUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetStoredPublicHolidayUseCase
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetStoredPublicHolidayUseCaseImpl
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
    abstract fun bindLoadPublicHolidaysUseCase(
        useCase: LoadPublicHolidaysUseCaseImpl
    ): LoadPublicHolidaysUseCase

    @Binds
    abstract fun bindGetAllStoredPublicHolidaysUseCase(
        useCase: GetAllStoredPublicHolidaysUseCaseImpl
    ): GetAllStoredPublicHolidaysUseCase

    @Binds
    abstract fun bindGetStoredPublicHolidayUseCase(
        useCase: GetStoredPublicHolidayUseCaseImpl
    ): GetStoredPublicHolidayUseCase


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