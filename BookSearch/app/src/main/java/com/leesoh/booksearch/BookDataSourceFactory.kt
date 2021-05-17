package com.leesoh.booksearch

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList

class BookDataSourceFactory(): DataSource.Factory<String, Item>() {

    val mutableLiveData: MutableLiveData<BookItemKeyedDataSource> = MutableLiveData()
    private var repoDataSource: BookItemKeyedDataSource? = null

    override fun create(): DataSource<String, Item> {
        Log.d("LSH", "BookDataSource - create");
        repoDataSource = BookItemKeyedDataSource()
        mutableLiveData.postValue(repoDataSource)
        return repoDataSource!!
    }

}