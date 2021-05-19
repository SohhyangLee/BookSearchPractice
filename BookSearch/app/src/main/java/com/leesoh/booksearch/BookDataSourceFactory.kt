package com.leesoh.booksearch

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class BookDataSourceFactory(val loadedItems: MutableList<Item>, val viewModelScope: CoroutineScope, var query: String?): DataSource.Factory<Int, Item>() {

    val mutableLiveData: MutableLiveData<BookItemKeyedDataSource> = MutableLiveData()
    var repoDataSource: BookItemKeyedDataSource? = null

    override fun create(): DataSource<Int, Item> {
        repoDataSource = BookItemKeyedDataSource(loadedItems, viewModelScope, query)
        mutableLiveData.postValue(repoDataSource)
        return requireNotNull(repoDataSource)
    }

    fun invalidateDate(){
        repoDataSource?.invalidate()
    }

}