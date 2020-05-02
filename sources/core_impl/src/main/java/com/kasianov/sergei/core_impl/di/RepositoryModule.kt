package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.memory.WikiMemoryCache
import com.kasianov.sergei.core_api.repository.UserRepo
import com.kasianov.sergei.core_api.repository.WikiInfoRepo
import com.kasianov.sergei.core_impl.repository.UserRepoImpl
import com.kasianov.sergei.core_impl.repository.WikiInfoRepoImpl
import com.kasianov.sergei.core_impl.repository.WikiMemoryCacheImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsWikiCache(memoryCache: WikiMemoryCacheImpl): WikiMemoryCache

    @Binds
    @Singleton
    abstract fun bindUserRepo(repo: UserRepoImpl): UserRepo

    @Binds
    @Singleton
    abstract fun bindWikiInfoRepo(repo: WikiInfoRepoImpl): WikiInfoRepo

}