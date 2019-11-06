package com.kasianov.sergei.omaloma.di

import androidx.room.Room
import com.kasianov.sergei.omaloma.Utils.DATA_BASE_NAME
import com.kasianov.sergei.omaloma.db.OmaLomaDb
import com.kasianov.sergei.omaloma.ui.company.CompanyViewModel
import com.kasianov.sergei.omaloma.ui.users.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

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

    viewModel {
        UsersViewModel()
    }

    viewModel {
        CompanyViewModel()
    }
}