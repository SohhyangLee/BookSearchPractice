package com.leesoh.booksearch

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class BookViewModel(var query: String?) : ViewModel() {
    val bookInfoLiveData: LiveData<PagedList<Item>> by lazy {
        getAllTitles()
    }

    private var _hasResult: MutableLiveData<Boolean> = MutableLiveData()
    val hasResult: LiveData<Boolean> = _hasResult

    lateinit var dataSourceFactory: BookDataSourceFactory

    private fun getAllTitles(): LiveData<PagedList<Item>> {
        dataSourceFactory = BookDataSourceFactory(viewModelScope, query) {
            _hasResult.postValue(it)
        }
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