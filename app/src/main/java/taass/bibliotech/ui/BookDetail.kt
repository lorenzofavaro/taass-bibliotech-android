package taass.bibliotech.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import taass.bibliotech.R
import taass.bibliotech.model.Category
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.book_item.*
import kotlinx.android.synthetic.main.book_item.view.*

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

        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .centerCrop()
            .into(book_detail_image)

        book_title.text = intent.getStringExtra("title")
        book_author.text = intent.getStringExtra("author")
        book_description.text = intent.getStringExtra("description")
        book_stock.text = intent.getLongExtra("stock", 0).toString()

        categories =
            Gson().fromJson(intent.getStringExtra("categories"), Array<Category>::class.java)
                .asList()
        var categories_text = ""
        for (category in categories) {
            categories_text = categories_text + " " + category.name
        }

        book_categories.text = categories_text
    }


}