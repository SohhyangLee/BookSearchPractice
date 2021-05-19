package com.leesoh.booksearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
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
        bundle.apply {
            putString("title", item?.title)
            putString("author", item?.author)
            putString("image", item?.image) //item image가 empty or null 일 때 처리
            //image 사이즈가 너무 클 떄는 어떻게 처리? -> resize를 어떻게 해야할쥐
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
