package com.leesoh.booksearch.model

import androidx.paging.DataSource
import com.leesoh.booksearch.data.BookItemKeyedDataSource
import kotlinx.coroutines.CoroutineScope

class BookDataSourceFactory(val viewModelScope: CoroutineScope, var query: String?, val hasResult: (Boolean) -> Unit): DataSource.Factory<Int, Item>() {

    var repoDataSource: BookItemKeyedDataSource? = null

    override fun create(): DataSource<Int, Item> {
        repoDataSource = BookItemKeyedDataSource(
            viewModelScope,
            query,
            hasResult
        )
        return requireNotNull(repoDataSource)
    }

    fun invalidateDate(){
        repoDataSource?.invalidate()
    }

}