package com.kasianov.sergei.omaloma.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.core.OmaLomaApp
import com.kasianov.sergei.omaloma.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as OmaLomaApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

}
