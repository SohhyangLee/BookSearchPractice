package com.leesoh.booksearch

import android.content.Context
import android.util.Log
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class BookItemKeyedDataSource(): ItemKeyedDataSource<String, Item>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Item>) {
        Log.d("LSH", "BookItemKeyedDataSource - loadInitial");
        val initKey = 1
        val items = getTitleItems(initKey, params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Item>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Item>) {
        Log.i("LSH","BookItemKeyedDataSource - loadAfter")
        //val items = getTitleItems(params. + 1, params.requestedLoadSize)
        //callback.onResult(items)
    }

    override fun getKey(item: Item): String {
        TODO("Not yet implemented")
    }

    fun getTitleItems(key: Int, size: Int): List<Item> {
        val list = ArrayList<Item>()
        for(i in 0..(size -1)) {
            Log.d("LSH", "BookItemKeyedDataSource - list.get(i) + " + list.get(i));
            val itemKey = key + i
            list.add(Item())
        }
        return list
    }

}