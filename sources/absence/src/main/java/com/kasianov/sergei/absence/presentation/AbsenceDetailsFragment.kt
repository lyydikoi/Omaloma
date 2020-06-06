 package com.kasianov.sergei.absence.presentation

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.absence.R
import com.kasianov.sergei.absence.databinding.FragmentAbsenceDetailsBinding
import com.kasianov.sergei.absence.di.AbsenceComponent
import com.kasianov.sergei.core_api.AppWithFacade
import com.kasianov.sergei.core_api.extentions.*
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import android.view.ViewGroup as ViewGroup1

class AbsenceDetailsFragment : Fragment() {

    @Inject
    lateinit var calcDateUtils: CalcDateUtils

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AbsenceDetailsViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentAbsenceDetailsBinding
    private lateinit var absenceId: String
    private lateinit var absenceCalendar: CalendarLayout
    private val headerDateFormatter = DateTimeFormatter.ofPattern("EEE'\n'd MMM")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AbsenceComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup1?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbsenceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()

        //if (absenceId.isNotBlank()) {
            //viewModel.getAbsence(absenceId)
        //}
    }

    private fun setObservers() {
        /*viewModel.selectedAbsence.observe(viewLifecycleOwner, Observer {
            it.fieldName?.let {  }
            updateUi(it)
        })*/
    }

    private fun setUpUI() {
        absenceCalendar = binding.calendarAbsenceLayout
        absenceCalendar.setUpCalendar(
            calcDateUtils,
            { startDate, endDate -> /*TODO: here dates are saved*/ },
            { startDate, endDate -> bindSummaryViews(startDate, endDate) }
        )

        bindSummaryViews()
    }

    private fun bindSummaryViews(startDate: LocalDate? = null, endDate: LocalDate? = null) {
        if (startDate != null) {
            binding.tvStartDate.text = headerDateFormatter.format(startDate)
            binding.tvStartDate.setTextColorRes(R.color.colorTextSecondary)
        } else {
            binding.tvStartDate.text = getString(R.string.start_date)
            binding.tvStartDate.setTextColor(Color.GRAY)
        }
        if (endDate != null) {
            binding.tvEndDate.text = headerDateFormatter.format(endDate)
            binding.tvEndDate.setTextColorRes(R.color.colorTextSecondary)
        } else {
            binding.tvEndDate.text = getString(R.string.end_date)
            binding.tvEndDate.setTextColor(Color.GRAY)
        }
    }

    private fun updateUi() {
        // UI related logic here
    }
}
