package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.memory.MemoryCacheProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import com.kasianov.sergei.core_api.repository.RepositoryProvider
import com.kasianov.sergei.core_impl.repository.WikiMemoryCacheImpl_Factory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class],
    dependencies = [DataBaseProvider::class, NetworkProvider::class, MemoryCacheProvider::class]
)
interface RepositoryComponent : RepositoryProvider