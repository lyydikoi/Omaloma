package com.kasianov.sergei.public_holidays.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.core_api.viewmodel.ViewModelKey
import com.kasianov.sergei.public_holidays.presentation.PubHolDetailsViewModel
import com.kasianov.sergei.public_holidays.presentation.PubHolListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class PublicHolidaysModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryProvider): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PubHolDetailsViewModel::class)
    abstract fun bindPubHolDetailsViewModel(viewModel: PubHolDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PubHolListViewModel::class)
    abstract fun bindPubHolListViewModel(viewModel: PubHolListViewModel): ViewModel

}