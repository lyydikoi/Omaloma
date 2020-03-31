package com.kasianov.sergei.core_impl.di

import android.content.Context
import com.kasianov.sergei.core_api.AppProvider
import com.kasianov.sergei.core_api.database.DataBaseProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DataBaseModule::class]
)
interface DataBaseComponent : DataBaseProvider {

    companion object {
        private var dbComponent: DataBaseProvider? = null

        fun create(appProvider: AppProvider): DataBaseProvider {
            return dbComponent ?: DaggerDataBaseComponent
                .builder()
                .appProvider(appProvider)
                .build().also {
                    dbComponent = it
                }
        }
    }

}