package com.example.techinfo.Fragments.Admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.R

class admin_adapter(
    private val context: Context,
    private val itemList: ArrayList<String>,
    private val onItemClick: (String, Int) -> Unit
) : RecyclerView.Adapter<admin_adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.details)
        val itemImage: ImageView = itemView.findViewById(R.id.image)

        init {
            itemView.setOnClickListener {
                onItemClick(itemList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = itemList[position]
    }

    override fun getItemCount(): Int = itemList.size
}
