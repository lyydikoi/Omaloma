package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.memory.WikiMemoryCache
import com.kasianov.sergei.core_impl.repository.WikiMemoryCacheImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MemoryCacheModule {

    @Binds
    @Singleton
    abstract fun bindWikiMemoryCache(memoryCache: WikiMemoryCacheImpl): WikiMemoryCache

}