package com.kasianov.sergei.core_api.repository

interface RepositoryProvider {

    fun provideWikiRepo(): WikiInfoRepo

    fun provideUserRepo(): UserRepo

}