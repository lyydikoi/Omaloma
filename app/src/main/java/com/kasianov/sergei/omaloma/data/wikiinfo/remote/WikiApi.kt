package com.kasianov.sergei.omaloma.data.wikiinfo.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("api.php")
    suspend fun getWikiSearchResults(
        @Query("srsearch") srsearch: String,
        @Query("action") action: String = "query",
        @Query("list") list: String = "search",
        @Query("srlimit") srlimit: String = "1",
        @Query("srnamespace") srnamespace: String = "0",
        @Query("format") format: String = "json"
    ): Response<WikiSearchResponseDTO>

    @GET("api.php")
    suspend fun getWikiPages(
        @Query("pageids") pagesid: String,
        @Query("action") action: String = "query",
        @Query("prop") prop: String = "info|description|extracts",
        @Query("format") format: String = "json",
        @Query("inprop") inprop: String = "url"
    ): Response<WikiPagesResponseDTO>

    @GET("api.php")
    suspend fun getWikiImages(
        @Query("pageids") pagesid: String,
        @Query("action") action: String = "query",
        @Query("prop") prop: String = "imageinfo",
        @Query("iiprop") iiprop: String = "url",
        @Query("generator") generator: String = "images",
        @Query("format") format: String = "json",
        @Query("inprop") inprop: String = "url"
    ): Response<WikiPagesResponseDTO>

}