package com.kasianov.sergei.omaloma.di

import androidx.room.Room
import com.kasianov.sergei.omaloma.Utils.DATA_BASE_NAME
import com.kasianov.sergei.omaloma.data.repositories.UserRepository
import com.kasianov.sergei.omaloma.data.repositories.UserRepositoryImpl
import com.kasianov.sergei.omaloma.data.source.local.OmaLomaDb
import com.kasianov.sergei.omaloma.ui.company.CompanyViewModel
import com.kasianov.sergei.omaloma.ui.publicholidays.PubHolViewModel
import com.kasianov.sergei.omaloma.ui.users.UserDetailsViewModel
import com.kasianov.sergei.omaloma.ui.users.UsersListViewModel
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

    single {
        get<OmaLomaDb>().userDao()
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    viewModel {
        UsersListViewModel()
    }

    viewModel {
        UserDetailsViewModel(get(), get())
    }

    viewModel {
        CompanyViewModel()
    }

    viewModel {
        PubHolViewModel()
    }
}