package com.leesoh.booksearch.data

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.leesoh.booksearch.model.BookInfo
import com.leesoh.booksearch.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BookItemKeyedDataSource(val viewModelScope: CoroutineScope, var query: String?, val hasResult:(Boolean) -> Unit): ItemKeyedDataSource<Int, Item>() {
    private val loadedItems: MutableList<Item> = mutableListOf()
    private lateinit var response : BookInfo

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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                response = NaverBookService().getApiService().getSearchBook(
                    NaverBookService.CLIENT_ID, NaverBookService.CLIENT_SECRET, query, 10, initKey)
                loadedItems.addAll(response.items)
                callback.onResult(response.items)
                hasResult.invoke(true)
            } catch (e: HttpException) {
                Log.i("BookItemKeyedDataSource", "result : " + e.code())
                hasResult.invoke(false)
            }
        }
    }

    override fun getKey(item: Item): Int {
        return loadedItems.indexOf(item)
    }
}