package taass.bibliotech.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taass.bibliotech.R
import taass.bibliotech.model.StudyHall

class StudyHallsAdapter(private val mList: List<StudyHall>) : RecyclerView.Adapter<StudyHallsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.study_hall_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val studyHall = mList[position]

        holder.imageView.setImageResource(R.drawable.img)
        holder.name.text = studyHall.name
        holder.address.text = studyHall.address
        holder.desc.text = "Posti disponibili: " + studyHall.availability.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.thumbnail)
        val name: TextView = itemView.findViewById(R.id.title)
        val address: TextView = itemView.findViewById(R.id.address)
        val desc: TextView = itemView.findViewById(R.id.desc)
    }
}