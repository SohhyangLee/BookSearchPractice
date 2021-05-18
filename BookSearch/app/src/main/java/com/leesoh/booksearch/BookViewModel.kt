package com.leesoh.booksearch

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class BookViewModel(val lifecycleCoroutineScope: LifecycleCoroutineScope) : ViewModel() {
    val bookInfoLiveData: LiveData<PagedList<Item>> by lazy {
        getAllTitles()
    }

    private fun getAllTitles(): LiveData<PagedList<Item>> {
        Log.d("LSH", "BookViewModel - getAllTitles")
        val dataSourceFactory = BookDataSourceFactory(mutableListOf(), lifecycleCoroutineScope)
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()
        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        return data.build()
    }
}