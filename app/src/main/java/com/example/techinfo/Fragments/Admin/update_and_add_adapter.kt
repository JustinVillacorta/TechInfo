package com.example.techinfo.Fragments.Admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.R

class update_and_add_adapter(
    private val dataList: List<update_and_add_data_class>  // Changed to accept admin_data_class
) : RecyclerView.Adapter<update_and_add_adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val modelNameTextView: TextView = itemView.findViewById(R.id.model_name_label)
        val specsTextView: TextView = itemView.findViewById(R.id.Specs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_update_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.modelNameTextView.text = item.modelName  // Corrected to match update_and_add_data_class property
        holder.specsTextView.text = item.specs          // Corrected to match update_and_add_data_class property
    }

    override fun getItemCount() = dataList.size
}
