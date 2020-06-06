package com.kasianov.sergei.core.di

import com.kasianov.sergei.core_api.AppProvider
import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.memory.MemoryCacheProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import com.kasianov.sergei.core_api.repository.RepositoryProvider
import com.kasianov.sergei.core_api.utils.UtilsProvider
import com.kasianov.sergei.core_impl.di.*

object CoreProvidersFactory {

    fun createDataBaseProvider(appProvider: AppProvider) : DataBaseProvider {
        return DataBaseComponent.create(appProvider)
    }

    fun createMemoryCacheProvider() : MemoryCacheProvider {
        return DaggerMemoryCacheComponent.create()
    }

    fun createNetworkServiceProvider() : NetworkProvider {
        return NetworkComponent.create()
    }

    fun createRepositoryProvider(
        dataBaseProvider: DataBaseProvider,
        networkProvider: NetworkProvider,
        memoryCacheProvider: MemoryCacheProvider
    ) : RepositoryProvider {
        return DaggerRepositoryComponent.builder()
            .dataBaseProvider(dataBaseProvider)
            .networkProvider(networkProvider)
            .memoryCacheProvider(memoryCacheProvider)
            .build()
    }

    fun createUtilsProvider() : UtilsProvider {
        return DaggerUtilsComponent.create()
    }

}