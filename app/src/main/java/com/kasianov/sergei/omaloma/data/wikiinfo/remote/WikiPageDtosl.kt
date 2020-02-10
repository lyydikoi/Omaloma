package com.kasianov.sergei.omaloma.data.wikiinfo.remote

import com.google.gson.annotations.SerializedName

data class WikiPagesResponseDTO(
    @SerializedName("query")
    val pagesSet: PagesSetDTO?
)

data class PagesSetDTO(
    @SerializedName("pages")
    val pages: HashMap<String, ArticleDTO>?
)

data class ArticleDTO(
    @SerializedName("pageId")
    val pageId: Int?,
    @SerializedName("ns")
    val ns: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("contentModel")
    val contentModel: String?,
    @SerializedName("pageLanguage")
    val pageLanguage: String?,
    @SerializedName("pageLanguageHtmlCode")
    val pageLanguageHtmlCode: String?,
    @SerializedName("pageLanguageDir")
    val pageLanguageDir: String?,
    @SerializedName("touched")
    val touched: String?,
    @SerializedName("lastRevId")
    val lastRevId: Int?,
    @SerializedName("length")
    val length: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("descriptionSource")
    val descriptionSource: String?,
    @SerializedName("fullUrl")
    val fullUrl: String?,
    @SerializedName("imageinfo")
    val images: List<ImageInfoDTO>?,
    @SerializedName("extract")
    val extract: String?
)

data class ImageInfoDTO(
    @SerializedName("url")
    val url: String?
)