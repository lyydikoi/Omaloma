package com.kasianov.sergei.omaloma.ui

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class OmaLomaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}