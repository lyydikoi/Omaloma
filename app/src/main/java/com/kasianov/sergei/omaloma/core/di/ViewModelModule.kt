package com.kasianov.sergei.omaloma.core.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.omaloma.core.di.ViewModelFactoryProvider
import com.kasianov.sergei.omaloma.core.di.ViewModelKey
import com.kasianov.sergei.omaloma.presentation.company.CompanyViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolListViewModel
import com.kasianov.sergei.omaloma.presentation.users.UserDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.users.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(
        factory:  ViewModelFactoryProvider
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CompanyViewModel::class)
    abstract fun bindCompanyViewModel(viewModel: CompanyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PubHolDetailsViewModel::class)
    abstract fun bindPubHolDetailsViewModel(viewModel: PubHolDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PubHolListViewModel::class)
    abstract fun bindPubHolListViewModel(viewModel: PubHolListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindUsersListViewModel(viewModel: UsersListViewModel): ViewModel

}