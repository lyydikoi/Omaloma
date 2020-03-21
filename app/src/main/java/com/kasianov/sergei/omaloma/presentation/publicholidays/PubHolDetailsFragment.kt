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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.presentation.utils.fromHtml
import com.kasianov.sergei.omaloma.databinding.FragmentPubHolidayDetailsBinding
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.presentation.publicholidays.adapter.ImagesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PubHolDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPubHolidayDetailsBinding
    private val viewModel by viewModel<PubHolDetailsViewModel>()
    private val adapter = ImagesListAdapter { position -> viewModel.setSelectedImage(position) }
    private var publicHolidayName= ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPubHolidayDetailsBinding.inflate(inflater, container, false)

        arguments?.let {
            it.getString(KEY_PUBLIC_HOLIDAY_NAME)?.let { name -> publicHolidayName = name }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setObservers()

        if (publicHolidayName.isNotBlank()) {
            viewModel.getPublicHoliday(publicHolidayName)
        }
    }

    private fun setObservers() {
        viewModel.selectedPubHoliday.observe(viewLifecycleOwner, Observer{
                viewModel.getWikiPageInfo(it.localName)
                updateUi(it)
        })

        viewModel.wikiArticle.observe(viewLifecycleOwner, Observer {
            showWikiInfo(it)
            viewModel.getImagesUrlsList(it.pageId.toString())
        })

        viewModel.wikiImagesUrlsList.observe(viewLifecycleOwner, Observer { images ->
            binding.rwBottomBar.visibility = if (images.isNotEmpty()) VISIBLE else GONE
            adapter.swapData(images)
        })

        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { if (it.isNotBlank()) showAppBarImage(it) }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.pbLoading.visibility = if (it) VISIBLE else GONE
        })
    }

    private fun setUpUI() {
        binding.rwBottomBar.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        binding.rwBottomBar.adapter = adapter
    }

    private fun updateUi(holiday: PublicHoliday) {
        binding.tvTitle.text = holiday.localName
        binding.tvDate.text = holiday.dateFormatted
    }

    private fun showWikiInfo(data: WikiArticle) {
        binding.tvContent.text = data.extract.fromHtml()
    }

    private fun showAppBarImage(url: String) {
        Glide.with(binding.ivPubHolidayImage.context)
            .load(url)
            .placeholder(R.drawable.ic_image_light_green_48dp)
            .into(binding.ivPubHolidayImage)
    }

}