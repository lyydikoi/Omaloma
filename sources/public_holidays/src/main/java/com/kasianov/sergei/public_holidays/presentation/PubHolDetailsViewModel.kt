package com.kasianov.sergei.public_holidays.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.extentions.Event
import com.kasianov.sergei.core_api.extentions.RequestResult
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import com.kasianov.sergei.core_api.model.dto.WikiArticleDTO
import com.kasianov.sergei.core_api.repository.WikiInfoRepo
import com.kasianov.sergei.public_holidays.data.PublicHolidaysRepo
import javax.inject.Inject

class PubHolDetailsViewModel @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo,
    private val wikiInfoRepo: WikiInfoRepo
) : BaseViewModel() {

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHolidayDTO>() }
    val selectedPubHoliday: LiveData<PublicHolidayDTO>
        get() = _selectedPubHoliday

    private val _wikiArticle by lazy { MutableLiveData<WikiArticleDTO>() }
    val wikiArticle: LiveData<WikiArticleDTO>
        get() = _wikiArticle

    private val _wikiImagesUrlsList by lazy { MutableLiveData<List<String>>() }
    val wikiImagesUrlsList: LiveData<List<String>>
        get() = _wikiImagesUrlsList

    private val _selectedImageUrl by lazy { MutableLiveData<String>() }
    val selectedImageUrl: LiveData<Event<String>> = Transformations.map(_selectedImageUrl) {
        Event<String>(it)
    }

    fun getPublicHoliday(name: String) {
        launchDataLoad {
            publicHolidayRepo.getStoredPublicHoliday(name, DEFAULT_YEAR, DEFAULT_COUNTRY).let {
                _selectedPubHoliday.postValue(it)
            }
        }
    }

    fun getWikiPageInfo(searchValue: String) {
        launchDataLoad {
            when (val searchResult = wikiInfoRepo.performWikiSearch(searchValue)) {
                is RequestResult.Success<String> -> getWikiArticleInfo(searchResult.data)
                is RequestResult.Error -> handleResponse(searchResult)
            }
        }
    }

    private fun getWikiArticleInfo(pageId: String) {
        launchDataLoad {
            handleResponse(wikiInfoRepo.getWikiPageInfo(pageId), _wikiArticle)
        }
    }

    fun getImagesUrlsList(pageId: String) {
        launchDataLoad {
            handleResponse(wikiInfoRepo.getWikiImageUrlsList(pageId), _wikiImagesUrlsList)
        }
    }

    fun setSelectedImage(position: Int) {
        wikiImagesUrlsList.value?.let {
            if (it.size > position) _selectedImageUrl.postValue(it[position])
        }
    }
}
