package com.leesoh.booksearch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView

class BookTitleListViewHolder(parent: ViewGroup, view: View) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_booklist, parent, false)) {
    val listTitle: TextView = view.findViewById(R.id.listTitle)

    fun bind(repo: List<Item>, position: Int){
        Log.d("LSH", "BookTitleListViewHolder - bind + repo.get(position).title : " + repo.get(position).title);
        listTitle.text = repo.get(position).title ?: "Searching..."
        //onClickListener
    }
}