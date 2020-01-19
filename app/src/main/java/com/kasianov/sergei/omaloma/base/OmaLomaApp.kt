package com.kasianov.sergei.omaloma.base

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kasianov.sergei.omaloma.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OmaLomaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin{
            androidLogger()
            androidContext(this@OmaLomaApp)
            modules(appModule)
        }

    }

}