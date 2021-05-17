package com.leesoh.booksearch

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverAPI {
    @GET("v1/search/book.json")
    suspend fun getSearchBook(
        @Header("X-Naver-Client-Id") cliendId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): BookInfo}