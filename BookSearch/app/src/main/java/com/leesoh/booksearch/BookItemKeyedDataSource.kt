package com.leesoh.booksearch

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.launch

class BookItemKeyedDataSource(val loadedItems: MutableList<Item>, val lifecycleCoroutineScope: LifecycleCoroutineScope): ItemKeyedDataSource<Int, Item>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Item>) {
        Log.d("LSH", "BookItemKeyedDataSource - loadInitial");
        val initKey = 1
        lifecycleCoroutineScope.launch {
            Log.d("LSH", "BookViewModel - loadData")
            val response = NaverBookService().getApiService().getSearchBook(NaverBookService.CLIENT_ID, NaverBookService.CLIENT_SECRET, MainActivity.searchKeyword, 10, initKey)
            loadedItems.addAll(response.items)
            callback.onResult(response.items)
            Log.i("LSH", "BookViewModel - " + response.items)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Item>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Item>) {
        Log.i("LSH", "BookItemKeyedDataSource - loadAfter")

        lifecycleCoroutineScope.launch {
            Log.d("LSH", "BookViewModel - loadData")
            val response = NaverBookService().getApiService().getSearchBook(NaverBookService.CLIENT_ID, NaverBookService.CLIENT_SECRET, "안", 10, params.key)
            loadedItems.addAll(response.items)
            callback.onResult(response.items)
            Log.i("LSH", "BookViewModel - " + response.items)
        }
        //val items = getTitleItems(params. + 1, params.requestedLoadSize)
        //callback.onResult(items)
    }

    override fun getKey(item: Item): Int {
        return loadedItems.indexOf(item)
    }
}