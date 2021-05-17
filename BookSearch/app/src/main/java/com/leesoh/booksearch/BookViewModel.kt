package com.leesoh.booksearch

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.launch

class BookViewModel(val lifecycleCoroutineScope: LifecycleCoroutineScope): ViewModel() {
    private var _bookInfoList: MutableLiveData<List<Item>> = MutableLiveData()
    val bookInfoList: LiveData<List<Item>> = _bookInfoList

    fun loadData() {
        lifecycleCoroutineScope.launch {
            Log.d("LSH", "BookViewModel - loadData")
            val response = NaverBookService().getApiService().getSearchBook(NaverBookService.CLIENT_ID, NaverBookService.CLIENT_SECRET, "안드로이드")
            _bookInfoList.postValue(response.items)
            Log.i("LSH", "BookViewModel - " + _bookInfoList.postValue(response.items))
        }
    }

    fun getAllTitles(): LivePagedListBuilder<String, Item> {
        Log.d("LSH", "BookViewModel - getAllTitles")
        val dataSourceFactory = BookDataSourceFactory()
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()
        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        return data
    }
}