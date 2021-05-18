package com.leesoh.booksearch

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView

class BookTitleListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val listTitle: TextView = view.findViewById(R.id.listTitle)
    var view: View = view

    fun bind(item: Item){
        Log.d("LSH", "BookTitleListViewHolder - bind + repo.get(position).title : " + item.title)
        val title1: String = (item.title).replace("<b>", "")
        val title2: String = title1.replace("</b>", "")
        listTitle.text = title2
    }
}