package com.leesoh.booksearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_searchtext.*
import java.lang.Thread.sleep
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit  var bookListAdapter: BookListAdapter
    val viewModel by lazy {
        BookViewModel(lifecycleScope) //여기에 query 넘겨주고 주고 하면 될 듯
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("LSH", "MainActivity - onCreate");
        bookListAdapter = BookListAdapter()
        recyclerResultView.adapter = bookListAdapter
        recyclerResultView.layoutManager = LinearLayoutManager(this)
        recyclerResultView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        textChanged()
        viewModel.bookInfoLiveData.observe(this) {
            Log.d("LSH", "MainActivity - observe")
            bookListAdapter.submitList(it)
        }
        bookListAdapter.notifyDataSetChanged()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            Log.i("LSH", "MainActivity - afterTextChanged s : " + s)

            val timer = Timer()
            timer.schedule(object:TimerTask(){
                override fun run() {
                    Log.i("LSH", "MainActivity - thread s : " + s)
                    searchKeyword = s.toString()
                }
            }, 1000)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Log.i("LSH", "MainActivity - beforeTextChanged s" + s + "/start : " + start + "/count: " + count + "/after: " + after)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.i("LSH", "MainActivity - onTextChanged : " + s + "/start : " + start + "/count: " + count + "/before: " + before)
        }
    }

    fun textChanged(){
        searchText.addTextChangedListener(textWatcher)
    }

    companion object {
        var searchKeyword = ""
    }
}