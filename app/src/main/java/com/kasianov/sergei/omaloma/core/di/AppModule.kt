package com.kasianov.sergei.omaloma.core.di

import androidx.room.Room
import com.kasianov.sergei.omaloma.core.*
import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.network.dto.PublicHolidayDTO
import com.kasianov.sergei.omaloma.data.network.dto.WikiImageInfoDTO
import com.kasianov.sergei.omaloma.data.repository.UserRepoImpl
import com.kasianov.sergei.omaloma.data.repository.PublicHolidaysRepoImpl
import com.kasianov.sergei.omaloma.data.repository.WikiInfoRepoImpl
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.model.mappers.*
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import com.kasianov.sergei.omaloma.domain.repository.UserRepo
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import com.kasianov.sergei.omaloma.domain.usecases.*
import com.kasianov.sergei.omaloma.presentation.company.CompanyViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolListViewModel
import com.kasianov.sergei.omaloma.presentation.users.UserDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.users.UsersListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Koin DI
val appModule = module {

    // Provide DB
    single {
        Room
            .databaseBuilder(androidContext(), OmaLomaDb::class.java, DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    // Provide ROOM DAOs
    single { get<OmaLomaDb>().userDao() }
    single { get<OmaLomaDb>().publicHolidayDao()}
    single { get<OmaLomaDb>().absenceDao() }

    // Provide singleton of Wiki Retrofit API service
    factory (named("wikiOkHttp")){ provideWikiOkHttpClient() }
    single (named("wikiRetrofit")){ provideWikiRetrofit(get(named("wikiOkHttp"))) }
    single (named("wikiApi")){ provideWikiApi(get(named("wikiRetrofit"))) }

    // Provide singleton of Public Holidays Retrofit API service
    factory (named("pubHolOkHttp")){ providePubHolOkHttpClient() }
    single (named("pubHolRetrofit")){ providePubHolRetrofit(get(named("pubHolOkHttp"))) }
    single (named("pubHolApi")){ providePubHolApi(get(named("pubHolRetrofit"))) }

    // Provide mappers
    factory (named("mapperDTOToPublicHoliday")) { MapperDTOToPublicHoliday() }
    factory <ListMapper<PublicHolidayDTO, PublicHoliday>> (named("listMapperDTOToPublicHoliday")) {
        ListMapperImpl(get(named("mapperDTOToPublicHoliday")))
    }

    factory (named("mapperDBToPublicHoliday")) { MapperDBToPublicHoliday() }
    factory <ListMapper<DBPublicHoliday, PublicHoliday>> (named("listMapperDBToPublicHoliday")) {
        ListMapperImpl(get(named("mapperDBToPublicHoliday")))
    }

    factory (named("mapperDTOToDBPublicHoliday")) { MapperDTOToDBPublicHoliday() }
    factory <ListMapper<PublicHolidayDTO, DBPublicHoliday>> (named("listMapperDTOToDBPublicHoliday")) {
        ListMapperImpl(get(named("mapperDTOToDBPublicHoliday")))
    }

    factory { MapperDTOToWikiArticle() }

    factory (named("mapperDTOToWikiImagesUlsList")) { MapperDTOToWikiImagesUlsList() }
    factory <ListMapper<WikiImageInfoDTO, String>> (named("listMapperDTOToWikiImagesUlsList")) {
        ListMapperImpl(get(named("mapperDTOToWikiImagesUlsList")))
    }

    // Repositories
    single<UserRepo> { UserRepoImpl(get()) }

    single<PublicHolidaysRepo> {
        PublicHolidaysRepoImpl(
            get(named("pubHolApi")),
            get(),
            get(named("listMapperDTOToDBPublicHoliday")),
            get(named("listMapperDTOToPublicHoliday")),
            get(named("listMapperDBToPublicHoliday")),
            get(named("mapperDBToPublicHoliday"))
        )
    }

    single<WikiInfoRepo> {
        WikiInfoRepoImpl(
            get(named("wikiApi")),
            get(),
            get((named("listMapperDTOToWikiImagesUlsList")))
        )
    }

    // Provide use cases
    factory<LoadPublicHolidaysUseCase> { LoadPublicHolidaysUseCaseImpl(get()) }
    factory<GetAllStoredPublicHolidaysUseCase> { GetAllStoredPublicHolidaySUseCaseImpl(get()) }
    factory<GetStoredPublicHolidayUseCase> { GetStoredPublicHolidayUseCaseImpl(get()) }
    factory<GetWikiUrlsListUseCase> { GetWikiUrlsListUseCaseImpl(get()) }
    factory<GetWikiPageInfoUseCase> { GetWikiPageInfoUseCaseImpl(get()) }
    factory<PerformWikiSearchUseCase> { PerformWikiSearchUseCaseImpl(get(), get()) }

    // ViewModels
    viewModel { UsersListViewModel() }
    viewModel { UserDetailsViewModel() }
    viewModel { PubHolListViewModel(get(), get()) }
    viewModel { PubHolDetailsViewModel(get(), get(), get()) }
    viewModel { CompanyViewModel() }

}