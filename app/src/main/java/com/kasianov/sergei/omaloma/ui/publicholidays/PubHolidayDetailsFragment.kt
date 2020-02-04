package com.kasianov.sergei.omaloma.ui.publicholidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.Utils.fromHtml
import com.kasianov.sergei.omaloma.data.source.remote.dtos.ArticleDto
import com.kasianov.sergei.omaloma.data.source.remote.dtos.PublicHolidayDto
import com.kasianov.sergei.omaloma.databinding.FragmentPubHolidayDetailsBinding
import com.kasianov.sergei.omaloma.ui.publicholidays.adapter.ImageSwipeRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PubHolidayDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPubHolidayDetailsBinding
    private val viewModel by sharedViewModel<PubHolListViewModel>()
    private var adapter = ImageSwipeRecyclerViewAdapter()
    private val linearSnapHelper = LinearSnapHelper()
    private val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPubHolidayDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()
    }

    private fun setObservers() {
        viewModel.selectedPubHoliday.observe(viewLifecycleOwner, Observer{ event ->
            event.getContentIfNotHandled()?.let {
                it.localName?.let { name -> viewModel.performWikiSearch(name) }
                updateUi(it)
            }
        })
        viewModel.wikiArticle?.let {
            it.observe(viewLifecycleOwner, Observer { article ->
                article?.let { data -> showWikiInfo(data) }
            })
        }

        viewModel.wikiImages?.let {
            it.observe(viewLifecycleOwner, Observer { images ->
                if (!images.isNullOrEmpty()) {
                    binding.rwBottomBar.visibility = VISIBLE
                    adapter.items = images
                } else {
                    binding.rwBottomBar.visibility = GONE
                }
            })
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.pbLoading.visibility = if (it) VISIBLE else GONE
        })
    }

    private fun setUpUI() {
        binding.rwBottomBar.layoutManager = linearLayoutManager
        binding.rwBottomBar.adapter = adapter
        linearSnapHelper.attachToRecyclerView(binding.rwBottomBar)
    }

    private fun updateUi(holyday: PublicHolidayDto) {
        binding.tvTitle.text = holyday.localName
        binding.tvDate.text = holyday.date
    }

    private fun showWikiInfo(data: ArticleDto) {
        data.extract?.let { binding.tvContent.text = it.fromHtml()}
    }

}