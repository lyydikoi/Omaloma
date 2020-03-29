package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.memory.WikiMemoryCache
import com.kasianov.sergei.core_impl.repository.WikiMemoryCacheImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MemoryCacheModule {

    @Binds
    abstract fun bindWikiMemoryCache(memoryCache: WikiMemoryCacheImpl): WikiMemoryCache

}