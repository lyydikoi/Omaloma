package com.kasianov.sergei.omaloma.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

}
