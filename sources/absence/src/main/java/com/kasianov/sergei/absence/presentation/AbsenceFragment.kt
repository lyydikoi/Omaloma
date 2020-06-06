package com.kasianov.sergei.absence.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasianov.sergei.absence.databinding.FragmentAbsenceBinding

class AbsenceFragment : Fragment() {
    // TODO: NOT implemented yet.
    private lateinit var binding: FragmentAbsenceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbsenceBinding.inflate(inflater, container, false)
        return binding.root
    }
}

sealed class AbsenceType {
    object Holiday : AbsenceType()
    object SickLeave : AbsenceType()
    object ChildSickLeave : AbsenceType()
    object ParentsLeave : AbsenceType()
    object MoveDays : AbsenceType()
    class DaysOff(val isPaid: Boolean): AbsenceType()
    class CeremonyDay(val title: String): AbsenceType()
}

sealed class AbsenceStatus {
    object Planned: AbsenceStatus()
    object DateReached: AbsenceStatus()
    object UsageConfirmed: AbsenceStatus()
}
