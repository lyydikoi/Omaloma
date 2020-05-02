package com.kasianov.sergei.absence.presentation.absences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasianov.sergei.absence.databinding.FragmentAbsencesListBinding


class AbsencesListFragment : Fragment() {

    // TODO: NOT implemented yet.

    private lateinit var binding: FragmentAbsencesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbsencesListBinding.inflate(inflater, container, false)
        return binding.root
    }

}
