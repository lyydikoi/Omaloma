package com.kasianov.sergei.omaloma.data.wikiinfo.remote

import com.google.gson.annotations.SerializedName

data class WikiSearchResponseDTO(
    @SerializedName("query")
    val query: QueryDTO?
)

data class QueryDTO(
    @SerializedName("search")
    val search: List<SearchInfoDTO>?
)

data class SearchInfoDTO(
    @SerializedName("pageId")
    val pageId: String?
)