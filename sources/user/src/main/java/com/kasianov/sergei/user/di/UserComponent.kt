package com.kasianov.sergei.user.di

import com.kasianov.sergei.core_api.ProvidersFacade
import com.kasianov.sergei.core_api.di_utils.LoggedUserScope
import com.kasianov.sergei.core_api.viewmodel.ViewModelsProvider
import com.kasianov.sergei.user.presentation.UserDetailsFragment
import com.kasianov.sergei.user.presentation.UsersListFragment
import dagger.Component

@LoggedUserScope
@Component(dependencies = [ProvidersFacade::class, ViewModelsProvider::class])
interface UserComponent {

    @Component.Factory
    interface Factory {
        fun create(providersFacade: ProvidersFacade, viewModelsProvider: ViewModelsProvider): UserComponent
    }

    fun inject(fragment: UsersListFragment)
    fun inject(fragment: UserDetailsFragment)
}