package com.kasianov.sergei.omaloma.core.di

import com.kasianov.sergei.omaloma.data.memorycache.WikiMemoryCache
import com.kasianov.sergei.omaloma.data.memorycache.WikiMemoryCacheImpl
import com.kasianov.sergei.omaloma.data.repository.PublicHolidaysRepoImpl
import com.kasianov.sergei.omaloma.data.repository.UserRepoImpl
import com.kasianov.sergei.omaloma.data.repository.WikiInfoRepoImpl
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import com.kasianov.sergei.omaloma.domain.repository.UserRepo
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
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
    abstract fun bindPubHolRepo(repo: PublicHolidaysRepoImpl): PublicHolidaysRepo

    @Binds
    @Singleton
    abstract fun bindUserRepo(repo: UserRepoImpl): UserRepo

    @Binds
    @Singleton
    abstract fun bindWikiInfoRepo(repo: WikiInfoRepoImpl): WikiInfoRepo

}