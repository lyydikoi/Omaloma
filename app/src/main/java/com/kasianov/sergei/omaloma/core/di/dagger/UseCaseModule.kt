package com.kasianov.sergei.omaloma.core.di.dagger
import com.kasianov.sergei.omaloma.domain.usecases.GetAllStoredPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.GetAllStoredPublicHolidaysUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.LoadPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.LoadPublicHolidaysUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.GetStoredPublicHolidayUseCase
import com.kasianov.sergei.omaloma.domain.usecases.GetStoredPublicHolidayUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.PerformWikiSearchUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.PerformWikiSearchUseCase
import com.kasianov.sergei.omaloma.domain.usecases.GetWikiPageInfoUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.GetWikiPageInfoUseCase
import com.kasianov.sergei.omaloma.domain.usecases.GetWikiUrlsListUseCaseImpl
import com.kasianov.sergei.omaloma.domain.usecases.GetWikiUrlsListUseCase
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