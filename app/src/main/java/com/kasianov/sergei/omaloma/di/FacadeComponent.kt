package com.kasianov.sergei.omaloma.di

import android.app.Application
import com.kasianov.sergei.core.di.CoreProvidersFactory
import com.kasianov.sergei.core_api.AppProvider
import com.kasianov.sergei.core_api.ProvidersFacade
import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.memory.MemoryCacheProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import com.kasianov.sergei.core_api.repository.RepositoryProvider
import com.kasianov.sergei.core_api.utils.UtilsProvider
import com.kasianov.sergei.omaloma.OmaLomaApp
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
        DataBaseProvider::class,
        NetworkProvider::class,
        MemoryCacheProvider::class,
        RepositoryProvider::class,
        UtilsProvider::class
    ]
)
interface FacadeComponent : ProvidersFacade {

    companion object {
        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .dataBaseProvider(CoreProvidersFactory.createDataBaseProvider(AppComponent.create(application)))
                .networkProvider(CoreProvidersFactory.createNetworkServiceProvider())
                .memoryCacheProvider(CoreProvidersFactory.createMemoryCacheProvider())
                .repositoryProvider(
                    CoreProvidersFactory.createRepositoryProvider(
                    CoreProvidersFactory.createDataBaseProvider(AppComponent.create(application)),
                    CoreProvidersFactory.createNetworkServiceProvider()
                ))
                .utilsProvider(CoreProvidersFactory.createUtilsProvider())
                .build()
    }

    fun inject(app: OmaLomaApp)

}