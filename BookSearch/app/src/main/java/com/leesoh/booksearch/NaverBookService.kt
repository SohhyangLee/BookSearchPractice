package com.leesoh.booksearch

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class NaverBookService {
    fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService() = getRetrofit().create(NaverAPI::class.java)


    private operator fun get(apiURL: String, requestHeaders: Map<String, String>): String{
        val con = connect(apiURL)
        try{
            con.requestMethod = "GET"
            for((key, value) in requestHeaders){
                con.setRequestProperty(key, value)
            }

            val responseCode = con.responseCode
            return if(responseCode == HttpURLConnection.HTTP_OK){
                readBody(con.inputStream)
            } else {
                readBody(con.errorStream)
            }
        } catch (e: IOException){
            throw RuntimeException("API 요청과 응답 실패", e)
        } finally {
            con.disconnect()
        }
    }

    private fun connect(apiURL: String): HttpURLConnection {
        try{
            val url = URL(apiURL)
            return url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException){
            throw RuntimeException("API URL이 잘못되었습니다. : $apiURL", e)
        } catch (e: IOException){
            throw RuntimeException("연결이 실패했습니다. : $apiURL", e)
        }
    }

    private fun readBody(body: InputStream): String{
        val streamReader = InputStreamReader(body)

        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()

                var line: String? = lineReader.readLine()
                while ( line != null) {
                    responseBody.append(line)
                    line = lineReader.readLine()
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }
    }

    private fun parseData(responseBody: String){
        var title: String
        var link: String
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(responseBody)
            val jsonArray = jsonObject.getJSONArray("items")

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                title = item.getString("title")
                link = item.getString("link")
                println("TITLE : $title / LINK : $link")
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val CLIENT_ID = "lqKxrxlQUW7iGEQpaNHa"
        const val CLIENT_SECRET = "3SDipYfCWH"
        const val BASE_URL_NAVER_API = "https://openapi.naver.com"
    }
}