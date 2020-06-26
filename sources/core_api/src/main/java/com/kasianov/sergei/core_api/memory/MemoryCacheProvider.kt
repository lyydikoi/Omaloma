package com.kasianov.sergei.core_api.memory

interface MemoryCacheProvider {
    fun provideWikiMemoryCache(): WikiMemoryCache
}