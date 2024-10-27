package com.example.techinfo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.R

data class ComponentItem(val name: String, val id: String)

class admin_adapter(
    private val context: Context,
    private val componentList: List<ComponentItem>,
    private val onItemClick: (ComponentItem, Int) -> Unit
) : RecyclerView.Adapter<admin_adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.componentName) // Update with actual ID

        init {
            itemView.setOnClickListener {
                onItemClick(componentList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_component, parent, false) // Update with your item layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val componentItem = componentList[position]
        holder.nameTextView.text = componentItem.name
    }

    override fun getItemCount(): Int {
        return componentList.size
    }
}
