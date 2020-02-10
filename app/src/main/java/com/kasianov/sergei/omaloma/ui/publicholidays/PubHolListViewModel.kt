package com.kasianov.sergei.omaloma.ui.publicholidays

import androidx.lifecycle.*
import com.kasianov.sergei.omaloma.utils.Event
import com.kasianov.sergei.omaloma.data.source.remote.NetworkUtils
import com.kasianov.sergei.omaloma.data.source.remote.dtos.ArticleDto
import com.kasianov.sergei.omaloma.data.source.remote.dtos.PublicHolidayDto
import com.kasianov.sergei.omaloma.data.source.remote.dtos.WikiPagesResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PubHolListViewModel : ViewModel() {

    // TOOD: use DI
    private val pubHolApiService = NetworkUtils.pubHolApiService
    private val wikiApiService = NetworkUtils.wikiApiService

    private val _publicHolidays by lazy { MutableLiveData<List<PublicHolidayDto>>() }

    val publicHolidays: LiveData<List<PublicHolidayDto>>
        get() = _publicHolidays

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHolidayDto>() }

    val selectedPubHoliday: LiveData<Event<PublicHolidayDto>>
        get() = Transformations.map(_selectedPubHoliday) { Event<PublicHolidayDto>(it) }

    private val _selectedImageUrl by lazy { MutableLiveData<String>() }

    val selectedImageUrl: LiveData<Event<String>>
        get() = Transformations.map(_selectedImageUrl) { Event<String>(it) }

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMsg by lazy { MutableLiveData<String>() }

    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _wikiArticleResponse = MutableLiveData<WikiPagesResponseDto>()

    val wikiArticle: LiveData<ArticleDto>? = Transformations.map(_wikiArticleResponse) {
        var result: ArticleDto? = null
        try {
            it.pagesSet?.pages?.forEach { (key, value) ->
                result = value
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            result
        }
    }

    private val _wikiImagesResponse = MutableLiveData<WikiPagesResponseDto>()

    val wikiImageUrlList: LiveData<List<String>>? = Transformations.map(_wikiImagesResponse) {
        var result = mutableListOf<String>()
        try {
            it.pagesSet?.pages?.forEach { (key, value) ->
                value.images?.first()?.url?.let { url ->
                    if (!url.contains(".svg", ignoreCase = true)) result.add(url)
                }
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            result
        }
    }

    fun loadPubHolidays(year: String, countyCode: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    pubHolApiService.getPublicHolidays(year, countyCode)
                }
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) _publicHolidays.postValue(body)
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()
                    _errorMsg.postValue("Error body:  ${errorBody?.string()}")
                } else {
                    _errorMsg.postValue("${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMsg.postValue(e.message ?: e.toString())
            } finally {
                _loading.value = false
            }
        }
    }

    fun setHolidaySelected(position: Int) {
       _publicHolidays.value?.get(position)?.let {
           _selectedPubHoliday.value = it
       }
    }

    fun performWikiSearch(value: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    wikiApiService.getWikiSearchResults(value)
                }
                if (response.isSuccessful) {
                    val pageId = response.body()?.query?.search?.first()?.pageId
                    if (!pageId.isNullOrBlank()) {
                        getWikiPageInfo(pageId)
                        getWikiImages(pageId)
                    }
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()
                    _errorMsg.postValue("Error body:  ${errorBody?.string()}")
                } else {
                    _errorMsg.postValue("${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMsg.postValue(e.message ?: e.toString())
            } finally {
                _loading.value = false
            }
        }
    }

    private suspend fun getWikiPageInfo(pageId: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                wikiApiService.getWikiPages(pageId)
            }
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) _wikiArticleResponse.postValue(body)
            } else if (response.errorBody() != null) {
                val errorBody = response.errorBody()
                _errorMsg.postValue("Error body:  ${errorBody?.string()}")
            } else {
                _errorMsg.postValue("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            _errorMsg.postValue(e.message ?: e.toString())
        }
    }

    private suspend fun getWikiImages(pageId: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                wikiApiService.getWikiImages(pageId)
            }
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    _wikiImagesResponse.postValue(body)
                }
            } else if (response.errorBody() != null) {
                val errorBody = response.errorBody()
                _errorMsg.postValue("Error body:  ${errorBody?.string()}")
            } else {
                _errorMsg.postValue("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            _errorMsg.postValue(e.message ?: e.toString())
        }
    }

    fun setSelectedImageUrl(position: Int) {
        wikiImageUrlList?.value?.let {
            if (it.size > position) _selectedImageUrl.postValue(it[position]) }
    }

}

