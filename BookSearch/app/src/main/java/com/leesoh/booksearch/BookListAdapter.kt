package com.leesoh.booksearch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView


class BookListAdapter(private val list: List<Item>): PagedListAdapter<BookInfo, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("LSH", "BookListAdapter - onCreateViewHolder");
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_booklist, parent, false)
        return BookTitleListViewHolder(parent, view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("LSH", "BookListAdapter - onBindViewHolder");
        (holder as BookTitleListViewHolder).bind(list, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<BookInfo>(){
            override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean =
                    oldItem.start == newItem.start

            override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean =
                oldItem.start == newItem.start
            }
        }
    }
