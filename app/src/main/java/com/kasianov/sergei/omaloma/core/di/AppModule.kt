package com.kasianov.sergei.omaloma.core.di

import androidx.room.Room
import com.kasianov.sergei.omaloma.core.DATA_BASE_NAME
import com.kasianov.sergei.omaloma.data.user.UserRepoImpl
import com.kasianov.sergei.omaloma.core.OmaLomaDb
import com.kasianov.sergei.omaloma.data.user.UserDataContract
import com.kasianov.sergei.omaloma.presentation.company.CompanyViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolListViewModel
import com.kasianov.sergei.omaloma.presentation.users.UserDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.users.UsersListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room
            .databaseBuilder(androidContext(), OmaLomaDb::class.java, DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<OmaLomaDb>().userDao() }

    single<UserDataContract.UserRepository> { UserRepoImpl(get()) }

    viewModel { UsersListViewModel() }

    viewModel { UserDetailsViewModel(get(), get()) }

    viewModel { CompanyViewModel() }

    viewModel { PubHolListViewModel() }
}