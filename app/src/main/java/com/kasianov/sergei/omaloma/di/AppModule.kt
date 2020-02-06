package com.kasianov.sergei.omaloma.di

import com.kasianov.sergei.omaloma.ui.publicholidays.PubHolListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PubHolListViewModel() }

}