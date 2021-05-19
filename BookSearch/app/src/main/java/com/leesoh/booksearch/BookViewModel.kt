package com.leesoh.booksearch

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class BookViewModel(var query: String?) : ViewModel() {
    val bookInfoLiveData: LiveData<PagedList<Item>> by lazy {
        getAllTitles()
    }

    lateinit var dataSourceFactory: BookDataSourceFactory

    private fun getAllTitles(): LiveData<PagedList<Item>> {
        dataSourceFactory = BookDataSourceFactory(mutableListOf(), viewModelScope, query)
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()
        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        return data.build()
    }

    fun invalidate(query: String?) {
        dataSourceFactory.invalidateDate()
        dataSourceFactory.query = query
    }
}