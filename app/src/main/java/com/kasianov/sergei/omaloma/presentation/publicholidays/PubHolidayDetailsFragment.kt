package com.kasianov.sergei.omaloma.presentation.publicholidays

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
import com.bumptech.glide.Glide
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.presentation.utils.fromHtml
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.ArticleDTO
import com.kasianov.sergei.omaloma.data.pubholiday.remote.PublicHolidayDTO
import com.kasianov.sergei.omaloma.databinding.FragmentPubHolidayDetailsBinding
import com.kasianov.sergei.omaloma.presentation.common.AdapterInteraction
import com.kasianov.sergei.omaloma.presentation.publicholidays.adapter.ImagesListAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PubHolidayDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPubHolidayDetailsBinding
    private val viewModel by sharedViewModel<PubHolListViewModel>()
    private val linearSnapHelper = LinearSnapHelper()
    private val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    private var adapter = ImagesListAdapter(object : AdapterInteraction {
        override fun itemClicked(position: Int) {
            viewModel.setSelectedImageUrl(position)
        }
    })

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

        viewModel.wikiImageUrlList?.let {
            it.observe(viewLifecycleOwner, Observer { images ->
                if (!images.isNullOrEmpty()) {
                    binding.rwBottomBar.visibility = VISIBLE
                    adapter.swapData(images)
                } else {
                    binding.rwBottomBar.visibility = GONE
                }
            })
        }

        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                if (it.isNotBlank()) showAppBarImage(it)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.pbLoading.visibility = if (it) VISIBLE else GONE
        })
    }

    private fun setUpUI() {
        binding.rwBottomBar.layoutManager = linearLayoutManager
        binding.rwBottomBar.adapter = adapter
        linearSnapHelper.attachToRecyclerView(binding.rwBottomBar)
    }

    private fun updateUi(holiday: PublicHolidayDTO) {
        binding.tvTitle.text = holiday.localName
        binding.tvDate.text = holiday.date
    }

    private fun showWikiInfo(data: ArticleDTO) {
        data.extract?.let { binding.tvContent.text = it.fromHtml()}
    }

    private fun showAppBarImage(url: String) {
        Glide.with(binding.ivPubHolidayImage.context)
            .load(url)
            .placeholder(R.drawable.ic_image_light_green_48dp)
            .into(binding.ivPubHolidayImage)
    }

}