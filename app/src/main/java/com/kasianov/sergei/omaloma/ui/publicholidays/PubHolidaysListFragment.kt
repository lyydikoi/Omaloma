package com.kasianov.sergei.omaloma.ui.publicholidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.utils.DEFAULT_YEAR
import com.kasianov.sergei.omaloma.utils.DEFAULT_COUNTRY
import com.kasianov.sergei.omaloma.databinding.FragmentPublicHolidaysListBinding
import com.kasianov.sergei.omaloma.ui.AdapterInteraction
import com.kasianov.sergei.omaloma.ui.publicholidays.adapter.PublicHolidaysListAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PubHolidaysListFragment : Fragment() {
    private lateinit var binding: FragmentPublicHolidaysListBinding

    private val viewModel by sharedViewModel<PubHolListViewModel>()

    private val adapter by lazy {
        PublicHolidaysListAdapter(object : AdapterInteraction {
            override fun itemClicked(position: Int) {
                viewModel.setHolidaySelected(position)
                findNavController()
                    .navigate(R.id.action_publicHolidaysListFragment_to_pubHolidayDetailsFragment)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadPubHolidays(DEFAULT_YEAR, DEFAULT_COUNTRY)

        viewModel.publicHolidays.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.swapData(it)
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
        binding = FragmentPublicHolidaysListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPubHolList.layoutManager=
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.rvPubHolList.adapter = adapter
    }

}