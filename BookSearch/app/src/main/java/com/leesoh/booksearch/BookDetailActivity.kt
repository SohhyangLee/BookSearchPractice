package com.leesoh.booksearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if(intent.hasExtra("title")){
            val title1 = (intent.getStringExtra("title"))?.replace("<b>", "")
            val title2 = title1?.replace("</b>", "")
            titleTextView.text = title2
        }
        if(intent.hasExtra("image")){
            Picasso.get().load(intent.getStringExtra("image")).error(R.drawable.ic_launcher_background).into(imageView)
        }
        if (intent.hasExtra("author")){
            authorTextView.text = intent.getStringExtra("author")
        }
        if (intent.hasExtra("price") && intent.hasExtra("discount")){
            priceTextView.text = intent.getStringExtra("price") + " / " + intent.getStringExtra("discount")
        }
        if (intent.hasExtra("description")){
            val description1 = (intent.getStringExtra("description"))?.replace("<b>", "")
            val description2 = description1?.replace("</b>", "")
            descriptionTextView.text = description2
        }
        if (intent.hasExtra("publisher")){
            publisherTextView.text = intent.getStringExtra("publisher")
        }
        if (intent.hasExtra("isbn")){
            isbnTextView.text = intent.getStringExtra("isbn")
        }
        if (intent.hasExtra("pubdate")){
            pubdateTextView.text = intent.getStringExtra("pubdate")
        }
    }
}