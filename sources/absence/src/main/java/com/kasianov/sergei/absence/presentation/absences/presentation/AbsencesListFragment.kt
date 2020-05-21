package com.kasianov.sergei.absence.presentation.absences.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.absence.databinding.FragmentAbsencesListBinding
import com.kasianov.sergei.absence.presentation.absences.di.AbsenceComponent
import com.kasianov.sergei.absence.presentation.absences.presentation.adapter.AbsencesListAdapter
import com.kasianov.sergei.core_api.AppWithFacade
import javax.inject.Inject


class AbsencesListFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AbsencesListViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentAbsencesListBinding
    private val adapter by lazy {
        AbsencesListAdapter { position -> /*viewModel.setSelectedImage(position)*/ }
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

        /*if (publicHolidayName.isNotBlank()) {
            viewModel.getPublicHoliday(publicHolidayName)
        }*/
    }

    private fun setObservers() {
/*        viewModel.selectedPubHoliday.observe(viewLifecycleOwner, Observer{
            it.localName?.let { localName -> viewModel.getWikiPageInfo(localName) }
            updateUi(it)
        })*/
    }

    private fun setUpUI() {
        /*binding.rwBottomBar.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        binding.rwBottomBar.adapter = adapter*/
    }
}
