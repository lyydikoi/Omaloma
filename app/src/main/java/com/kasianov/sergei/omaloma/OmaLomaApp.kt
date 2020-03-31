package com.kasianov.sergei.omaloma

import android.app.Application
import com.kasianov.sergei.core_api.AppWithFacade
import com.kasianov.sergei.core_api.ProvidersFacade
import com.kasianov.sergei.omaloma.di.FacadeComponent

class OmaLomaApp: Application(), AppWithFacade {

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade() : ProvidersFacade {

        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }

}