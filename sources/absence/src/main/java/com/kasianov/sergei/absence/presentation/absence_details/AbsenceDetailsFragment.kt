 
package com.kasianov.sergei.absence.presentation.absence_details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kasianov.sergei.absence.R
import com.kasianov.sergei.absence.databinding.FragmentAbsenceDetailsBinding
import com.kasianov.sergei.absence.di.AbsenceComponent
import com.kasianov.sergei.absence.presentation.CalendarLayout
import com.kasianov.sergei.absence.presentation.KEY_ABSENCE_CREATED_MILLIS
import com.kasianov.sergei.core_api.AppWithFacade
import com.kasianov.sergei.core_api.extentions.*
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import javax.inject.Inject
import android.view.ViewGroup as ViewGroup1

class AbsenceDetailsFragment : Fragment() {

    @Inject
    lateinit var calcDateUtils: CalcDateUtils

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AbsenceDetailsViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentAbsenceDetailsBinding
    private var absenceCreatedMillis: String? = null
    private lateinit var absenceCalendar: CalendarLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AbsenceComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup1?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbsenceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        absenceCreatedMillis = arguments?.getString(KEY_ABSENCE_CREATED_MILLIS)
        val args = arguments

        setUpUI()

        viewModel.uiState.observe(viewLifecycleOwner, Observer { updateUi(it) })

        if (savedInstanceState == null) {
            viewModel.handleAction(UIModelContract.Action.GetAbsence(absenceCreatedMillis))
        }
    }

    private fun setUpUI() {
        absenceCalendar = binding.calendarAbsenceLayout
        absenceCalendar.setUpCalendar(
            calcDateUtils = calcDateUtils,
            saveDatesInteraction = { startDate, endDate ->
                viewModel.handleAction(UIModelContract.Action.SetDate(startDate, endDate))
            },
            datesSelectedInteraction = { startDate, endDate ->
                bindCalendarSummaryViews(startDate, endDate)
            }
        )
        binding.ivOpenCalendar.setOnClickListener {
            viewModel.handleAction(UIModelContract.Action.ChangeDate)
        }
        binding.ivCloseCalendar.setOnClickListener {
            viewModel.handleAction(UIModelContract.Action.CloseCalendar)
        }
        binding.ivBack.setOnClickListener {
            viewModel.handleAction(UIModelContract.Action.CloseDetailsView)
        }
        binding.btnSaveAbsence.setOnClickListener {
            viewModel.handleAction(UIModelContract.Action.SaveAbsence)
        }
    }

    private fun bindCalendarSummaryViews(startDate: String? = null, endDate: String? = null) {
        if (!startDate.isNullOrBlank()) {
            binding.tvStartDate.text = startDate
            binding.tvStartDate.setTextColorRes(R.color.colorTextSecondary)
        } else {
            binding.tvStartDate.text = getString(R.string.start_date)
            binding.tvStartDate.setTextColor(Color.GRAY)
        }
        if (!startDate.isNullOrBlank()) {
            binding.tvEndDate.text = endDate
            binding.tvEndDate.setTextColorRes(R.color.colorTextSecondary)
        } else {
            binding.tvEndDate.text = getString(R.string.end_date)
            binding.tvEndDate.setTextColor(Color.GRAY)
        }
    }

    private fun updateUi(uiState: UIModelContract.UIState) {
        when (uiState) {
            is UIModelContract.UIState.ChoosingDate -> {
                binding.ivBack.visibility = GONE
                binding.ivOpenCalendar.visibility = INVISIBLE
                absenceCalendar.setDates(
                    uiState.startDate,
                    uiState.endDate
                )
                binding.calendarAbsenceLayout.visibility = VISIBLE
                binding.ivCloseCalendar.visibility = VISIBLE
                binding.btnSaveAbsence.visibility = GONE
            }
            is UIModelContract.UIState.Success -> {
                binding.calendarAbsenceLayout.visibility = GONE
                binding.ivCloseCalendar.visibility = GONE
                binding.ivBack.visibility = VISIBLE
                binding.ivOpenCalendar.visibility = VISIBLE
                binding.calendarAbsenceLayout.setDates(
                    uiState.absence.startDate,
                    uiState.absence.endDate
                )
                binding.btnSaveAbsence.visibility = VISIBLE
            }
            is UIModelContract.UIState.NavigatingBack -> findNavController().popBackStack()
        }
    }
}
