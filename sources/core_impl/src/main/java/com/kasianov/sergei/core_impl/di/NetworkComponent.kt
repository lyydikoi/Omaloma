package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.AppProvider
import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider {

    companion object {
        private var networkComponent: NetworkProvider? = null

        fun create(): NetworkProvider {
            return networkComponent ?: DaggerNetworkComponent
                .builder()
                .build().also {
                    networkComponent = it
                }
        }
    }

}
