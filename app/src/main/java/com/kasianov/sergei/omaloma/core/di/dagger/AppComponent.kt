package com.kasianov.sergei.omaloma.core.di.dagger

import android.content.Context
import com.kasianov.sergei.omaloma.presentation.MainActivity
import com.kasianov.sergei.omaloma.presentation.absences.AbsenceFragment
import com.kasianov.sergei.omaloma.presentation.company.CompanyFragment
import com.kasianov.sergei.omaloma.presentation.maincontent.MainContentFragment
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolDetailsFragment
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolListFragment
import com.kasianov.sergei.omaloma.presentation.users.UserDetailsFragment
import com.kasianov.sergei.omaloma.presentation.users.UsersListFragment
import com.kasianov.sergei.omaloma.presentation.utils.CalcDateUtils
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

    fun inject(activity: MainActivity)
    fun inject(fragment: PubHolDetailsFragment)
    fun inject(fragment: PubHolListFragment)

}