package com.kasianov.sergei.omaloma.core.di.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.omaloma.presentation.company.CompanyViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolListViewModel
import com.kasianov.sergei.omaloma.presentation.users.UserDetailsViewModel
import com.kasianov.sergei.omaloma.presentation.users.UsersListViewModel
import com.kasianov.sergei.omaloma.presentation.utils.OmaLomaViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: OmaLomaViewModelFactory): ViewModelProvider.Factory

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