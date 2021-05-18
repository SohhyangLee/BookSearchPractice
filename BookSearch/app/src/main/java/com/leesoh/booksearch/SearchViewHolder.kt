package com.leesoh.booksearch

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val searchText: TextView = view.findViewById(R.id.searchText)
    var view: View = view

    fun bind(item: Item){
        Log.d("LSH", "SearchViewHolder - bind")
    }
}