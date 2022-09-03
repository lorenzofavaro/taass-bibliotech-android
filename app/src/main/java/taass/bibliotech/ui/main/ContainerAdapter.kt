package taass.bibliotech.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import taass.bibliotech.R
import taass.bibliotech.model.Books
import taass.bibliotech.ui.BookDetail
import com.google.gson.Gson
import kotlinx.android.synthetic.main.book_item.view.*

class ContainerAdapter(private var items: List<Books>) :
    RecyclerView.Adapter<ContainerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Books = items[position]
        holder.chapterName.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picture)
            .into(holder.bookIcon)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, BookDetail::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("author", item.author)
            intent.putExtra("description", item.description)
            intent.putExtra("image", item.picture)
            intent.putExtra("categories", Gson().toJson(item.categories))
            intent.putExtra("stock", item.stock)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chapterName: AppCompatTextView = view.title
        val bookIcon: AppCompatImageView = view.bookIcon
    }
}
