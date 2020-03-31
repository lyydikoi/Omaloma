package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import com.kasianov.sergei.core_api.repository.RepositoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class],
    dependencies = [DataBaseProvider::class, NetworkProvider::class]
)
interface RepositoryComponent : RepositoryProvider