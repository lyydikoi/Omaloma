package com.kasianov.sergei.omaloma

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class OmaLomaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}