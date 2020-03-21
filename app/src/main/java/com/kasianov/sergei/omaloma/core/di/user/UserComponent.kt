package com.kasianov.sergei.omaloma.core.di.user

import com.kasianov.sergei.omaloma.core.di.LoggedUserScope
import com.kasianov.sergei.omaloma.core.di.AppComponent
import com.kasianov.sergei.omaloma.presentation.users.UserDetailsFragment
import com.kasianov.sergei.omaloma.presentation.users.UsersListFragment
import dagger.Component

@LoggedUserScope
@Component(dependencies = [AppComponent::class])
interface UserComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): UserComponent
    }

    fun inject(fragment: UsersListFragment)
    fun inject(fragment: UserDetailsFragment)
}