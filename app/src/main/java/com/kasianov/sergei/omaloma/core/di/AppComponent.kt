package com.kasianov.sergei.omaloma.core.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.omaloma.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MapperModule::class,
    NetworkModule::class,
    DataBaseModule::class,
    ViewModelModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
    UtilsModule::class
])
interface  AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun inject(activity: MainActivity)

}