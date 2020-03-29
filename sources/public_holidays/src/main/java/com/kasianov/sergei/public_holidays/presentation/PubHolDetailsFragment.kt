package com.kasianov.sergei.public_holidays.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kasianov.sergei.core.CoreProvidersFactory
import com.kasianov.sergei.core.fromHtml
import com.kasianov.sergei.core_api.AppWithFacade
import com.kasianov.sergei.core_api.extentions.EventObserver
import com.kasianov.sergei.core_api.model.WikiArticle
import com.kasianov.sergei.public_holidays.R
import com.kasianov.sergei.public_holidays.databinding.FragmentPubHolidayDetailsBinding
import com.kasianov.sergei.core_api.model.PublicHoliday
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import com.kasianov.sergei.core_api.model.dto.WikiArticleDTO
import com.kasianov.sergei.public_holidays.di.DaggerPublicHolidaysComponent
import com.kasianov.sergei.public_holidays.di.PublicHolidaysComponent
import com.kasianov.sergei.public_holidays.presentation.adapter.ImagesListAdapter
import javax.inject.Inject

class PubHolDetailsFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PubHolDetailsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentPubHolidayDetailsBinding

    private val adapter by lazy {
        ImagesListAdapter { position -> viewModel.setSelectedImage(position) }
    }

    private var publicHolidayName= ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PublicHolidaysComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

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
                it.localName?.let { localName -> viewModel.getWikiPageInfo(localName) }
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

        viewModel.selectedImageUrl.observe(viewLifecycleOwner,
            EventObserver { selectedImageUrl ->
                if (selectedImageUrl.isNotBlank()) showAppBarImage(selectedImageUrl)
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

    private fun updateUi(holiday: PublicHolidayDTO) {
        binding.tvTitle.text = holiday.localName
        binding.tvDate.text = holiday.date
    }

    private fun showWikiInfo(data: WikiArticleDTO) {
        data.extract?.let { articleExtract ->
            binding.tvContent.text = articleExtract.fromHtml()
        }
    }

    private fun showAppBarImage(url: String) {
        Glide.with(binding.ivPubHolidayImage.context)
            .load(url)
            .placeholder(R.drawable.ic_image_light_green_48dp)
            .into(binding.ivPubHolidayImage)
    }

}