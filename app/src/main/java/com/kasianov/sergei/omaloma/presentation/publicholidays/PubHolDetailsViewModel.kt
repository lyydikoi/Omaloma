package com.kasianov.sergei.omaloma.presentation.publicholidays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.omaloma.core.BaseViewModel
import com.kasianov.sergei.omaloma.core.extentions.Event
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.model.WikiArticle
import com.kasianov.sergei.omaloma.domain.usecases.GetStoredPublicHolidayUseCase
import com.kasianov.sergei.omaloma.domain.usecases.GetWikiUrlsListUseCase
import com.kasianov.sergei.omaloma.domain.usecases.PerformWikiSearchUseCase

class PubHolDetailsViewModel(
    private val getStoredPublicHolidayUseCase: GetStoredPublicHolidayUseCase,
    private val performWikiSearchUseCase: PerformWikiSearchUseCase,
    private val getWikiUrlsListUseCase: GetWikiUrlsListUseCase
) : BaseViewModel() {

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHoliday>() }
    val selectedPubHoliday: LiveData<PublicHoliday>
        get() = _selectedPubHoliday

    private val _wikiArticle by lazy { MutableLiveData<WikiArticle>() }
    val wikiArticle: LiveData<WikiArticle>
        get() = _wikiArticle

    private val _wikiImagesUrlsList by lazy { MutableLiveData<List<String>>() }
    val wikiImagesUrlsList: LiveData<List<String>>
        get() = _wikiImagesUrlsList

    private val _selectedImageUrl by lazy { MutableLiveData<String>() }
    val selectedImageUrl: LiveData<Event<String>>
        = Transformations.map(_selectedImageUrl) { Event<String>(it) }

    fun getPublicHoliday(name: String) {
        launchDataLoad {
            getStoredPublicHolidayUseCase(name).let {
                _selectedPubHoliday.postValue(it)
            }
        }
    }

    fun getWikiPageInfo(searchValue: String) {
        launchDataLoad {
            handleResponse(performWikiSearchUseCase(searchValue), _wikiArticle)
        }
    }

    fun getImagesUrlsList(pageId: String) {
        launchDataLoad {
            handleResponse(getWikiUrlsListUseCase(pageId), _wikiImagesUrlsList)
        }
    }

    fun setSelectedImage(position: Int) {
        wikiImagesUrlsList.value?.let {
            if (it.size > position) _selectedImageUrl.postValue(it[position])
        }
    }
}