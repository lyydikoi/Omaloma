package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.memory.MemoryCacheProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MemoryCacheModule::class]
)
interface MemoryCacheComponent : MemoryCacheProvider