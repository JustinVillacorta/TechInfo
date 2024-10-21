package com.example.techinfo.Fragments.BuildPCmodules

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.Fragments.BuildPC.ComponentData
import com.example.techinfo.R

class Adapter(
    private val componentList: MutableList<ComponentData>,
    private val itemClickListener: (ComponentData, Int) -> Unit
) : RecyclerView.Adapter<Adapter.ComponentViewHolder>() {

    inner class ComponentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val componentImageView: ImageView = view.findViewById(R.id.image)
        val componentNameTextView: TextView = view.findViewById(R.id.details)

        fun bind(component: ComponentData, position: Int) {
            Log.d("Adapter", "Binding Component: ${component.name}, Part Details: ${component.partDetails}")
            componentNameTextView.text = if (component.partDetails.isNotEmpty()) component.partDetails else component.name
            componentImageView.setImageResource(getIconResource(component.type))
            itemView.setBackgroundColor(if (component.isSelected) Color.LTGRAY else Color.WHITE)

            itemView.setOnClickListener {
                itemClickListener(component, position)
                component.isSelected = !component.isSelected
                itemView.setBackgroundColor(if (component.isSelected) Color.LTGRAY else Color.WHITE)
                notifyItemChanged(position)
            }

            // Set up long click listener for unselecting items
            itemView.setOnLongClickListener {
                showUnselectConfirmationDialog(component, position)
                true // Indicate that the long click was handled
            }
        }

        private fun showUnselectConfirmationDialog(component: ComponentData, position: Int) {
            AlertDialog.Builder(itemView.context)
                .setTitle("Unselect Item")
                .setMessage("Are you sure you want to unselect ${component.name}?")
                .setPositiveButton("Yes") { dialog, which ->
                    unselectItem(position)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        private fun unselectItem(position: Int) {
            val component = componentList[position]
            if (component.isSelected) {
                component.isSelected = false // Unselect the item
                notifyItemChanged(position) // Notify the adapter to refresh the item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ComponentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder - Position: $position")
        holder.bind(componentList[position], position)
    }



    override fun getItemCount() = componentList.size

    private fun getIconResource(componentType: String): Int {
        return when (componentType) {
            "CPU" -> R.drawable.cpu
            "GPU" -> R.drawable.gpu
            "RAM" -> R.drawable.ram
            "SSD" -> R.drawable.ssd
            "HDD" -> R.drawable.hdd
            "PSU" -> R.drawable.psu
            "Case" -> R.drawable.computercase
            "Motherboard" -> R.drawable.motherboard
            "CPU Cooler" -> R.drawable.cooler
            else -> R.drawable.baseline_buildpc_24
        }
    }
}
