package com.kasianov.sergei.absence.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasianov.sergei.absence.R
import com.kasianov.sergei.absence.databinding.FragmentAbsencesListBinding
import com.kasianov.sergei.absence.di.AbsenceComponent
import com.kasianov.sergei.absence.presentation.adapter.AbsencesListAdapter
import com.kasianov.sergei.core_api.AppWithFacade
import com.kasianov.sergei.core_api.extentions.EventObserver
import javax.inject.Inject

const val KEY_ABSENCE_CREATED_MILLIS = "absence_created_millis"

class AbsencesListFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AbsencesListViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentAbsencesListBinding
    private val adapter by lazy {
        AbsencesListAdapter { position -> viewModel.setAbsenceSelected(position) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AbsenceComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbsencesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()
    }

    private fun setObservers() {
        viewModel.selectedAbsence.observe(viewLifecycleOwner, EventObserver { selectedAbsence ->
            if (findNavController().currentDestination?.id == R.id.absencesListFragment) {
                findNavController().navigate(
                    R.id.absenceDetailsFragment,
                    bundleOf(KEY_ABSENCE_CREATED_MILLIS to selectedAbsence.createdMillis)
                )
            }
        })

        viewModel.absencesList.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.swapData(it)
        })
    }

    private fun setUpUI() {
        binding.fabCreateAbsence.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.absencesListFragment) {
                findNavController().navigate(R.id.absenceDetailsFragment)
            }
        }

        binding.rwAbsecesList.layoutManager = LinearLayoutManager(context)
        binding.rwAbsecesList.adapter = adapter
    }
}
