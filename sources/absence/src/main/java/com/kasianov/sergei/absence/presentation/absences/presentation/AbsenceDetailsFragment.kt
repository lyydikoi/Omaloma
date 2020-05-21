package com.kasianov.sergei.absence.presentation.absences.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.absence.databinding.FragmentAbsenceDetailsBinding
import com.kasianov.sergei.absence.presentation.absences.di.AbsenceComponent
import com.kasianov.sergei.core_api.AppWithFacade
import javax.inject.Inject

class AbsenceDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AbsenceDetailsViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentAbsenceDetailsBinding
    private lateinit var absenceId: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AbsenceComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbsenceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()

        if (absenceId.isNotBlank()) {
            //viewModel.getAbsence(absenceId)
        }
    }

    private fun setObservers() {
        /*viewModel.selectedAbsence.observe(viewLifecycleOwner, Observer {
            it.fieldName?.let {  }
            updateUi(it)
        })*/
    }

    private fun setUpUI() {
    }

    private fun updateUi() {
        // UI related logic here
    }
}
