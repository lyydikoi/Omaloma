package com.kasianov.sergei.omaloma.core

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kasianov.sergei.omaloma.core.di.dagger.AppComponent
import com.kasianov.sergei.omaloma.core.di.dagger.DaggerAppComponent

open class OmaLomaApp: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

    }

}