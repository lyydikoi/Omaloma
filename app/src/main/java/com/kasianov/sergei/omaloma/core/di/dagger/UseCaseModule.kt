package com.kasianov.sergei.omaloma.core.di.dagger

import com.kasianov.sergei.omaloma.domain.usecases.*
import com.kasianov.sergei.omaloma.domain.usecases.pubholusecases.*
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

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