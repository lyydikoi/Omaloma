package com.kasianov.sergei.omaloma.data.source.remote.dtos

import com.google.gson.annotations.SerializedName

data class WikiPagesResponseDto(
    @SerializedName("query")
    val pagesSet: PagesSetDto?
)

data class PagesSetDto(
    @SerializedName("pages")
    val pages: HashMap<String, ArticleDto>?
)

data class ArticleDto(
    @SerializedName("pageid")
    val pageid: Int?,
    @SerializedName("ns")
    val ns: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("contentmodel")
    val contentmodel: String?,
    @SerializedName("pagelanguage")
    val pagelanguage: String?,
    @SerializedName("pagelanguagehtmlcode")
    val pagelanguagehtmlcode: String?,
    @SerializedName("pagelanguagedir")
    val pagelanguagedir: String?,
    @SerializedName("touched")
    val touched: String?,
    @SerializedName("lastrevid")
    val lastrevid: Int?,
    @SerializedName("length")
    val length: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("descriptionsource")
    val descriptionsource: String?,
    @SerializedName("fullurl")
    val fullurl: String?,
    @SerializedName("imageinfo")
    val images: List<ImageInfoDto>?,
    @SerializedName("extract")
    val extract: String?
)

data class ImageInfoDto(
    @SerializedName("url")
    val url: String?
)