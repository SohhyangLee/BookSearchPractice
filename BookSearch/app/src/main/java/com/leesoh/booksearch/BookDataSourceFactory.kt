package com.leesoh.booksearch

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList

class BookDataSourceFactory(val loadedItems: MutableList<Item>, val lifecycleCoroutineScope: LifecycleCoroutineScope): DataSource.Factory<Int, Item>() {

    val mutableLiveData: MutableLiveData<BookItemKeyedDataSource> = MutableLiveData()
    private var repoDataSource: BookItemKeyedDataSource? = null

    override fun create(): DataSource<Int, Item> {
        Log.d("LSH", "BookDataSource - create");
        repoDataSource = BookItemKeyedDataSource(loadedItems, lifecycleCoroutineScope)
        mutableLiveData.postValue(repoDataSource)
        return repoDataSource!!
    }

}