package com.kasianov.sergei.omaloma.data.source.remote.dtos

import com.google.gson.annotations.SerializedName

data class WikiSearchResponseDto(
    @SerializedName("query")
    val query: QueryDto?
)

data class QueryDto(
    @SerializedName("search")
    val search: List<SearchInfoDto> = listOf()
)

data class SearchInfoDto(
    @SerializedName("pageid")
    val pageId: String?
)