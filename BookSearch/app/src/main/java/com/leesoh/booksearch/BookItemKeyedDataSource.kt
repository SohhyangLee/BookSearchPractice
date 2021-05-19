package com.leesoh.booksearch

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpURLConnection

class BookItemKeyedDataSource(val loadedItems: MutableList<Item>, val viewModelScope: CoroutineScope, var query: String?): ItemKeyedDataSource<Int, Item>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Item>) {
        val initKey = 1
        getSearchResult(initKey, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Item>) {
        // nothing to do
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Item>) {
        getSearchResult(params.key, callback)
    }

    private fun getSearchResult(initKey: Int, callback: LoadCallback<Item>) {
        viewModelScope.launch {
            try {
                val response = NaverBookService().getApiService().getSearchBook(NaverBookService.CLIENT_ID, NaverBookService.CLIENT_SECRET, query, 10, initKey)
                loadedItems.addAll(response.items)
                callback.onResult(response.items)
            } catch (e: HttpException) {
                Log.i("BookItemKeyedDataSource", "result : " + e.code())
            }
        }
    }

    override fun getKey(item: Item): Int {
        return loadedItems.indexOf(item)
    }
}