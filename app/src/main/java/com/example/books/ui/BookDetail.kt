package com.example.books.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.model.Books
import com.example.books.model.Category
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.product_item_layout.*
import kotlinx.android.synthetic.main.product_item_layout.view.*

class BookDetail : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var author: String
    private lateinit var description: String
    private lateinit var image: String
    private var stock: Long = 0
    private lateinit var categories: List<Category>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        title = intent.getStringExtra("title").toString()

        Glide.with(this)//.asGif()
            .load(intent.getStringExtra("image")) //.apply(RequestOptions.circleCropTransform())
            .centerCrop()
            .into(iv_product_detail_image)

        iv_product_detail_title.text = intent.getStringExtra("title")
        iv_product_detail_author.text = intent.getStringExtra("author")
        iv_product_detail_desc_value.text = intent.getStringExtra("description")
        iv_product_detail_stock_quantity_value.text = intent.getLongExtra("stock", 0).toString()

        categories = Gson().fromJson(intent.getStringExtra("categories"), Array<Category>::class.java).asList()
        var categories_text = ""
        for (category in categories) {
            categories_text = categories_text + " " + category.name
        }

        iv_product_detail_categoriesy_value.text = categories_text
    }


}