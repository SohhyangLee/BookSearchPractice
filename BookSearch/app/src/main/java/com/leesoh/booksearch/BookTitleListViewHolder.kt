package com.leesoh.booksearch

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookTitleListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val listTitle: TextView = view.findViewById(R.id.listTitle)

    fun bind(item: Item){
        listTitle.text = (item.title).replace("<b>", "").replace("</b>", "")
    }
}