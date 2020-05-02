package com.kasianov.sergei.core_api.model.dto

import com.google.gson.annotations.SerializedName

data class WikiSearchResponseDTO(
    @SerializedName("query")
    val query: QueryDTO?
)

data class QueryDTO(
    @SerializedName("search")
    val search: List<SearchInfoDTO> = emptyList()
)

data class SearchInfoDTO(
    @SerializedName("pageid")
    val pageId: String?
)