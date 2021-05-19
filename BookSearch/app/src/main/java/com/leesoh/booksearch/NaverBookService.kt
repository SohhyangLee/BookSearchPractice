package com.leesoh.booksearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverBookService {
    fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService() = getRetrofit().create(NaverAPI::class.java)

    companion object {
        const val CLIENT_ID = "lqKxrxlQUW7iGEQpaNHa"
        const val CLIENT_SECRET = "3SDipYfCWH"
        const val BASE_URL_NAVER_API = "https://openapi.naver.com"
    }
}