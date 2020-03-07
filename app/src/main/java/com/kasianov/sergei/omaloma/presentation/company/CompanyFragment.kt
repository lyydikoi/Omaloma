package com.kasianov.sergei.omaloma.presentation.company

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CompanyFragment : Fragment() {

    // TODO: NOT implemented yet.

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val companyViewModel: CompanyViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
