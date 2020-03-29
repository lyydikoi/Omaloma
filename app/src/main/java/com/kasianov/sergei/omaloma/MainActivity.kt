package com.kasianov.sergei.omaloma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import com.kasianov.sergei.core_api.AppWithFacade
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.databinding.ActivityMainBinding
import com.kasianov.sergei.omaloma.di.DaggerAppComponent
import com.kasianov.sergei.omaloma.di.DaggerMainComponent
import com.kasianov.sergei.omaloma.di.MainComponent

class MainActivity : AppCompatActivity() {

    //@Inject lateinit var pubHolDetailsMediator: PubHolDetailsMediator
    //@Inject lateinit var pubHolListMediator: PubHolListMediator

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.factory()
            .create((application as AppWithFacade).getFacade())
            .inject(this)

        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

}
