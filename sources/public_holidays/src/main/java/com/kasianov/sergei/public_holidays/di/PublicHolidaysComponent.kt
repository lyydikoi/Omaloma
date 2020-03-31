package com.kasianov.sergei.public_holidays.di

import com.kasianov.sergei.core_api.ProvidersFacade
import com.kasianov.sergei.public_holidays.presentation.PubHolDetailsFragment
import com.kasianov.sergei.public_holidays.presentation.PubHolListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [PublicHolidaysModule::class],
    dependencies = [ProvidersFacade::class]
)
interface PublicHolidaysComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): PublicHolidaysComponent {
            return DaggerPublicHolidaysComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(fragment: PubHolDetailsFragment)
    fun inject(fragment: PubHolListFragment)

}