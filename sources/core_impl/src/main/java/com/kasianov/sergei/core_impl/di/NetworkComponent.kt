package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.network.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider
