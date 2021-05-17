package com.leesoh.booksearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var bookListAdapter: BookListAdapter? = null

    val viewModel by lazy {
        BookViewModel(lifecycleScope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("LSH", "MainActivity - onCreate");
        viewModel.loadData()
        //viewModel.bookInfoList.observe(this) {
        viewModel.bookInfoList.observe(this) {
            bookListAdapter = BookListAdapter(it)
            Log.d("LSH", "MainActivity - observe")
            recyclerResultView.adapter = bookListAdapter
            recyclerResultView.layoutManager = LinearLayoutManager(this)
            recyclerResultView.addItemDecoration(
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            )
        }


    }
}