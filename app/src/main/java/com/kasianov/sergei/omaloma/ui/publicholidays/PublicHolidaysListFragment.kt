package com.kasianov.sergei.omaloma.ui.publicholidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.databinding.FragmentPublicHolidaysListBinding
import com.kasianov.sergei.omaloma.ui.publicholidays.recyclerview.PublicHolidaysAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PublicHolidaysListFragment : Fragment() {
    private lateinit var binding: FragmentPublicHolidaysListBinding
    private val viewModel by viewModel<PubHolViewModel>()
    private val adapter by lazy { PublicHolidaysAdapter() }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadPubHolidays()
        viewModel.publicHolidays.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.swapData(it)
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
        binding.fabShowPubHol.setOnClickListener {
            //this.findNavController().navigate(R.id.action_usersListFragment_to_userFragment)
        }
    }
}