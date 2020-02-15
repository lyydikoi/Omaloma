package com.kasianov.sergei.omaloma.presentation.publicholidays

import androidx.lifecycle.*
import com.kasianov.sergei.omaloma.presentation.utils.Event
import com.kasianov.sergei.omaloma.core.NetworkUtils
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.ArticleDTO
import com.kasianov.sergei.omaloma.data.pubholiday.remote.PublicHolidayDTO
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiPagesResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PubHolListViewModel : ViewModel() {

    // TOOD: use DI
    private val pubHolApiService = NetworkUtils.pubHolApiService
    private val wikiApiService = NetworkUtils.wikiApiService

    private val _publicHolidays by lazy { MutableLiveData<List<PublicHolidayDTO>>() }

    val publicHolidays: LiveData<List<PublicHolidayDTO>>
        get() = _publicHolidays

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHolidayDTO>() }

    val selectedPubHoliday: LiveData<Event<PublicHolidayDTO>>
        get() = Transformations.map(_selectedPubHoliday) { Event<PublicHolidayDTO>(it) }

    private val _selectedImageUrl by lazy { MutableLiveData<String>() }

    val selectedImageUrl: LiveData<Event<String>>
        get() = Transformations.map(_selectedImageUrl) { Event<String>(it) }

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMsg by lazy { MutableLiveData<String>() }

    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _wikiArticleResponse = MutableLiveData<WikiPagesResponseDTO>()

    val wikiArticle: LiveData<ArticleDTO>? = Transformations.map(_wikiArticleResponse) {
        var result: ArticleDTO? = null
        try {
            it.pagesSet?.pages?.forEach { (_, value) ->
                result = value
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            result
        }
    }

    private val _wikiImagesResponse = MutableLiveData<WikiPagesResponseDTO>()

    val wikiImageUrlList: LiveData<List<String>>? = Transformations.map(_wikiImagesResponse) {
        var result = mutableListOf<String>()
        try {
            it.pagesSet?.pages?.forEach { (_, value) ->
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
                withContext(Dispatchers.IO) {
                    val response =  pubHolApiService.getPublicHolidays(year, countyCode)
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) _publicHolidays.postValue(body)
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()
                        _errorMsg.postValue("Error body:  ${errorBody?.string()}")
                    } else {
                        _errorMsg.postValue("${response.code()} ${response.message()}")
                    }
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
                withContext(Dispatchers.IO) {
                    val response =  wikiApiService.getWikiSearchResults(value)
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
            withContext(Dispatchers.IO) {
                val response = wikiApiService.getWikiPages(pageId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) _wikiArticleResponse.postValue(body)
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()
                    _errorMsg.postValue("Error body:  ${errorBody?.string()}")
                } else {
                    _errorMsg.postValue("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            _errorMsg.postValue(e.message ?: e.toString())
        }
    }

    private suspend fun getWikiImages(pageId: String) {
        try {
            withContext(Dispatchers.IO) {
                val response = wikiApiService.getWikiImages(pageId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) _wikiImagesResponse.postValue(body)
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()
                    _errorMsg.postValue("Error body:  ${errorBody?.string()}")
                } else {
                    _errorMsg.postValue("${response.code()} ${response.message()}")
                }
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

