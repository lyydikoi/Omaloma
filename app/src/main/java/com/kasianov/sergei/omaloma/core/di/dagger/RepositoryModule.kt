package com.kasianov.sergei.omaloma.core.di.dagger

import com.kasianov.sergei.omaloma.data.repository.PublicHolidaysRepoImpl
import com.kasianov.sergei.omaloma.data.repository.UserRepoImpl
import com.kasianov.sergei.omaloma.data.repository.WikiInfoRepoImpl
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import com.kasianov.sergei.omaloma.domain.repository.UserRepo
import com.kasianov.sergei.omaloma.domain.repository.WikiInfoRepo
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPubHolRepo(repo: PublicHolidaysRepoImpl): PublicHolidaysRepo

    @Binds
    abstract fun bindUserRepo(repo: UserRepoImpl): UserRepo

    @Binds
    abstract fun bindWikiInfoRepo(repo: WikiInfoRepoImpl): WikiInfoRepo

}