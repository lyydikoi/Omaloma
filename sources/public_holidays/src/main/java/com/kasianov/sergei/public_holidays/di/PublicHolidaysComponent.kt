package com.kasianov.sergei.public_holidays.di

import com.kasianov.sergei.core.CoreProvidersFactory
import com.kasianov.sergei.core_api.ProvidersFacade
import com.kasianov.sergei.core_api.di_utils.FragmentScope
import com.kasianov.sergei.core_api.viewmodel.ViewModelsProvider
import com.kasianov.sergei.public_holidays.presentation.PubHolDetailsFragment
import com.kasianov.sergei.public_holidays.presentation.PubHolListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [PublicHolidaysModule::class],
    dependencies = [ProvidersFacade::class/*, ViewModelsProvider::class*/]
)
interface PublicHolidaysComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): PublicHolidaysComponent {
            return DaggerPublicHolidaysComponent
                .builder()
                //.viewModelsProvider(CoreProvidersFactory.createViewModelProvider())
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(fragment: PubHolDetailsFragment)
    fun inject(fragment: PubHolListFragment)

}