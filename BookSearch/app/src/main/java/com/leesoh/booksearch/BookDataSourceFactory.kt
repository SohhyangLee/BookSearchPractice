package com.leesoh.booksearch

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class BookDataSourceFactory(val viewModelScope: CoroutineScope, var query: String?, var hasResult: (Boolean) -> Unit): DataSource.Factory<Int, Item>() {

    val mutableLiveData: MutableLiveData<BookItemKeyedDataSource> = MutableLiveData()
    var repoDataSource: BookItemKeyedDataSource? = null

    override fun create(): DataSource<Int, Item> {
        repoDataSource = BookItemKeyedDataSource(viewModelScope, query, hasResult)
        mutableLiveData.postValue(repoDataSource)
        return requireNotNull(repoDataSource)
    }

    fun invalidateDate(){
        repoDataSource?.invalidate()
    }

}