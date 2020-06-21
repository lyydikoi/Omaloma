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
import com.kasianov.sergei.absence.R
import com.kasianov.sergei.absence.databinding.FragmentAbsenceDetailsBinding
import com.kasianov.sergei.absence.di.AbsenceComponent
import com.kasianov.sergei.absence.presentation.CalendarLayout
import com.kasianov.sergei.absence.presentation.KEY_ABSENCE_CREATED_MILLIS
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
    private var absenceCreatedMillis: String? = null
    private lateinit var absenceCalendar: CalendarLayout

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

        absenceCreatedMillis = arguments?.getString(KEY_ABSENCE_CREATED_MILLIS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()

        viewModel.uiState.observe(viewLifecycleOwner, Observer { updateUi(it) })
        viewModel.handleAction(UIModelContract.Action.GetAbsence(absenceCreatedMillis))
    }


    private fun setUpUI() {
        absenceCalendar = binding.calendarAbsenceLayout
        absenceCalendar.setUpCalendar(
            calcDateUtils,
            { startDate, endDate ->
                viewModel.handleAction(UIModelContract.Action.SetDate(startDate, endDate))
            },
            { startDate, endDate ->
                bindCalendarSummaryViews(startDate, endDate)
            }
        )
        bindCalendarSummaryViews()

        binding.ivOpenCalendar.setOnClickListener {
            viewModel.handleAction(UIModelContract.Action.ChangeDate)
        }
    }

    private fun bindCalendarSummaryViews(startDate: String? = null, endDate: String? = null) {
        if (startDate != null) {
            binding.tvStartDate.text = startDate
            binding.tvStartDate.setTextColorRes(R.color.colorTextSecondary)
        } else {
            binding.tvStartDate.text = getString(R.string.start_date)
            binding.tvStartDate.setTextColor(Color.GRAY)
        }
        if (endDate != null) {
            binding.tvEndDate.text = endDate
            binding.tvEndDate.setTextColorRes(R.color.colorTextSecondary)
        } else {
            binding.tvEndDate.text = getString(R.string.end_date)
            binding.tvEndDate.setTextColor(Color.GRAY)
        }
    }

    private fun updateUi(uiState: UIModelContract.UIState) {
        when(uiState) {
            is UIModelContract.UIState.ChoosingDate -> {
                binding.groupCalendar.visibility = VISIBLE
                binding.calendarAbsenceLayout.visibility = VISIBLE
                binding.ivBack.visibility = GONE
                binding.ivOpenCalendar.visibility = INVISIBLE
                absenceCalendar.setDates(
                    uiState.startDate,
                    uiState.endDate
                )
            }
            is UIModelContract.UIState.Success -> {
                binding.groupCalendar.visibility = GONE
                binding.ivBack.visibility = VISIBLE
                binding.ivOpenCalendar.visibility = VISIBLE
                binding.calendarAbsenceLayout.setDates(
                    uiState.absence.startDate,
                    uiState.absence.endDate
                )
            }
        }
    }
}
