package com.leesoh.booksearch.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.leesoh.booksearch.R
import com.leesoh.booksearch.model.Item


class BookListAdapter : PagedListAdapter<Item, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_booklist, parent, false)
        return BookTitleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            startDetailActivity(holder, getItem(position))
        }
        (holder as BookTitleListViewHolder).bind(requireNotNull(getItem(position)))
    }

    private fun startDetailActivity(holder: RecyclerView.ViewHolder, item: Item?){
        var intent = Intent(holder.itemView?.context, BookDetailActivity::class.java)
        var bundle = Bundle()
        bundle.apply {
            putString("title", item?.title)
            putString("author", item?.author)
            item?.let {
                if((item.image).isNotEmpty()) {
                    putString("image", item?.image)
                }
                else {
                    putString("image", "R.drawable.ic_launcher_background")
                }
            }
            putString("description", item?.description)
            putString("price", item?.price)
            putString("discount", item?.discount)
            putString("publisher", item?.publisher)
            putString("isbn", item?.isbn)
            putString("pubdate", item?.pubdate)
        }
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
