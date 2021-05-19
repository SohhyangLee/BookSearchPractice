package com.leesoh.booksearch.model

import com.google.gson.annotations.SerializedName

data class BookInfo(
        val start: Int = 0,
        val items: List<Item>
)

data class Item(
    @SerializedName("title")
    val title:String = "",
    @SerializedName("author")
    val author: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("discount")
    val discount: String = "",
    @SerializedName("publisher")
    val publisher: String = "",
    @SerializedName("isbn")
    val isbn: String = "",
    @SerializedName("pubdate")
    val pubdate: String = ""
)