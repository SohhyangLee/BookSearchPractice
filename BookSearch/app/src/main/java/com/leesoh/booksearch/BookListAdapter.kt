package com.leesoh.booksearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView


class BookListAdapter : PagedListAdapter<Item, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_booklist, parent, false)
        return BookTitleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            startDetailActivity(holder, getItem(position))
        }
        (holder as BookTitleListViewHolder).bind(requireNotNull(getItem(position))) //null 체크?
    }

    private fun startDetailActivity(holder: RecyclerView.ViewHolder, item: Item?){
        var intent = Intent(holder.itemView?.context, BookDetailActivity::class.java)
        var bundle = Bundle()
        bundle.putString("title", item?.title)
        bundle.putString("author", item?.author)
        bundle.putString("image", item?.image)
        bundle.putString("description", item?.description)
        bundle.putString("price", item?.price)
        bundle.putString("discount", item?.discount)
        bundle.putString("publisher", item?.publisher)
        bundle.putString("isbn", item?.isbn)
        bundle.putString("pubdate", item?.pubdate)
        intent.putExtras(bundle)
        ContextCompat.startActivity(holder.itemView.context, intent, null)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                    oldItem.title == newItem.title

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                    oldItem.title == newItem.title
        }
    }
}
