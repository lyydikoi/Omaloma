package com.kasianov.sergei.core_impl.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.core_api.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    /*@Provides
    //@Singleton
    fun viewModelsHolder():  @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel> {
        return mutableMapOf()
    }

    @Provides
    //@Singleton
    fun bindsFactory(
        map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }*/


    @Provides
    @Singleton
    fun viewModelsHolder(): @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel> {
        return mutableMapOf()
    }

    @Provides
    @Singleton
    fun bindsFactory(map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>): ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }

    //@Singleton
    //@Binds
    //abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /*@Binds
    @IntoMap
    @ViewModelKey(CompanyViewModel::class)
    abstract fun bindCompanyViewModel(viewModel: CompanyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindUsersListViewModel(viewModel: UsersListViewModel): ViewModel*/

}