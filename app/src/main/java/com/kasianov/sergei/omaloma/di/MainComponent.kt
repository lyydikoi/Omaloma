package com.kasianov.sergei.omaloma.di

import com.kasianov.sergei.core_api.ProvidersFacade
import com.kasianov.sergei.omaloma.MainActivity
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(providersFacade: ProvidersFacade): MainComponent
    }

    fun inject(activity: MainActivity)
}