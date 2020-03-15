package com.kasianov.sergei.omaloma.presentation.publicholidays

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.core.OmaLomaApp
import com.kasianov.sergei.omaloma.core.extentions.EventObserver
import com.kasianov.sergei.omaloma.databinding.FragmentPublicHolidaysListBinding
import com.kasianov.sergei.omaloma.presentation.publicholidays.adapter.PublicHolidaysListAdapter
import javax.inject.Inject

const val KEY_PUBLIC_HOLIDAY_NAME = "public_holiday_name"

class PubHolListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentPublicHolidaysListBinding
    private val viewModel: PubHolListViewModel by viewModels { viewModelFactory }
    private val adapter by lazy {
        PublicHolidaysListAdapter { position: Int -> viewModel.setHolidaySelected(position) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as OmaLomaApp).appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.publicHolidays.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.swapData(it)
        })

        viewModel.selectedPubHoliday.observe(viewLifecycleOwner, EventObserver { selectedPubHol ->
            findNavController().navigate(
                R.id.action_publicHolidaysListFragment_to_pubHolidayDetailsFragment,
                bundleOf(KEY_PUBLIC_HOLIDAY_NAME to selectedPubHol.localName))
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.pbLoading.visibility = if (it) VISIBLE else GONE
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPublicHolidaysListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPubHolList.layoutManager= LinearLayoutManager(context)
        binding.rvPubHolList.adapter = adapter
    }

}