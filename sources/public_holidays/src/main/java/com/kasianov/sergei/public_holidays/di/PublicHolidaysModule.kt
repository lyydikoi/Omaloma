package com.kasianov.sergei.public_holidays.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.core_api.repository.WikiInfoRepo
import com.kasianov.sergei.core_api.viewmodel.ViewModelFactory
import com.kasianov.sergei.core_api.viewmodel.ViewModelKey
import com.kasianov.sergei.public_holidays.ViewModelFactoryProvider
import com.kasianov.sergei.public_holidays.data.PublicHolidaysRepo
import com.kasianov.sergei.public_holidays.data.PublicHolidaysRepoImpl
import com.kasianov.sergei.public_holidays.presentation.PubHolDetailsViewModel
import com.kasianov.sergei.public_holidays.presentation.PubHolListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class PublicHolidaysModule {

    @Binds
    @Singleton
    abstract fun bindPubHolRepo(repo: PublicHolidaysRepoImpl): PublicHolidaysRepo

    /*@Module
    companion object {
        //@Singleton
        /*@Provides
        @IntoMap
        @ViewModelKey(PubHolDetailsViewModel::class)
        fun providePubHolListViewModel(
            pubHolRepo: PublicHolidaysRepo,
            wikiInfoRepo: WikiInfoRepo
        ): ViewModel = PubHolDetailsViewModel(pubHolRepo, wikiInfoRepo)

        //@Singleton
        Provides
        @IntoMap
        @ViewModelKey(PubHolListViewModel::class)
        fun providePubHolDetailsViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, Provider<ViewModel>>,
            pubHolRepo: PublicHolidaysRepo,
            wikiInfoRepo: WikiInfoRepo
        ): ViewModel = PubHolListViewModel(pubHolRepo)*/
        ////////////////////////////////////
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

        @Provides
        @Singleton
        @JvmStatic
        fun provideHomeViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>,ViewModel>,
            pubHolRepo: PublicHolidaysRepo,
            wikiInfoRepo: WikiInfoRepo
        ): ViewModel = PubHolListViewModel(pubHolRepo).also {
            map[PubHolListViewModel::class.java] = it
        }


        @Provides
        @Singleton
        @JvmStatic
        fun provideDummy(viewModel: ViewModel) = EagerTrigger()
        ////////////////////////////////////////////////////////////
    }

    class EagerTrigger*/

    /*@Binds
    @IntoMap
    @ViewModelKey(PubHolDetailsViewModel::class)
    abstract fun bindPubHolDetailsViewModel(viewModel: PubHolDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PubHolListViewModel::class)
    abstract fun bindPubHolListViewModel(viewModel: PubHolListViewModel): ViewModel*/

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactoryProvider
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PubHolDetailsViewModel::class)
    abstract fun bindPubHolDetailsViewModel(viewModel: PubHolDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PubHolListViewModel::class)
    abstract fun bindPubHolListViewModel(viewModel: PubHolListViewModel): ViewModel

}