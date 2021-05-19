package com.leesoh.booksearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leesoh.booksearch.view.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_searchtext.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit  var bookListAdapter: BookListAdapter
    val viewModel by lazy {
        BookViewModel(searchKeyword)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookListAdapter = BookListAdapter()
        recyclerResultView.adapter = bookListAdapter
        recyclerResultView.layoutManager = LinearLayoutManager(this)
        recyclerResultView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        viewModel.bookInfoLiveData.observe(this, Observer { data ->
            bookListAdapter.submitList(data)
        })

        viewModel.hasResult.observe(this, Observer {
            if (it) {
                recyclerResultView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
            } else {
                recyclerResultView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        })
        textChanged()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val timer = Timer()
            timer.schedule(object:TimerTask(){
                override fun run() {
                    searchKeyword = s.toString()
                    viewModel.invalidate(searchKeyword)
                }
            }, 1000)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    }

    private fun textChanged(){
        searchText.addTextChangedListener(textWatcher)
    }

    companion object {
        var searchKeyword = ""
    }
}

