package com.kasianov.sergei.omaloma.ui.publicholidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kasianov.sergei.omaloma.data.source.remote.dto.PublicHolidayDto
import com.kasianov.sergei.omaloma.databinding.FragmentPubHolidayDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PubHolidayDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPubHolidayDetailsBinding
    private val viewModel by viewModel<PubHolViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.selectedPubHoliday.observe(viewLifecycleOwner, Observer{ event ->
            event.getContentIfNotHandled()?.let { updateUi(it) }
        })
    }

    private fun updateUi(holyday: PublicHolidayDto) {
        binding.tvTitle.text = holyday.localName
        binding.tvDate.text = holyday.date
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPubHolidayDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

}