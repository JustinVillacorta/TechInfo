package com.example.techinfo.Fragments.Admin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.ui.ComponentItem
import com.example.techinfo.ui.admin_adapter
import com.example.techinfo.ui.Admin_catalog
import com.example.techinfo.R

class AdminView : Fragment() {
    private lateinit var recyclerViewAdmin: RecyclerView
    private lateinit var adminAdapter: admin_adapter
    private val adminList: ArrayList<ComponentItem> = arrayListOf(
        ComponentItem("CPU", R.drawable.cpu.toString()),
        ComponentItem("GPU", R.drawable.gpu.toString()),
        ComponentItem("RAM", R.drawable.ram.toString()),
        ComponentItem("Motherboard", R.drawable.motherboard.toString()),
        ComponentItem("SSD", R.drawable.ssd.toString()),
        ComponentItem("HDD", R.drawable.hdd.toString()),
        ComponentItem("PSU", R.drawable.psu.toString()),
        ComponentItem("Case", R.drawable.computercase.toString()),
        ComponentItem("CPU Cooler", R.drawable.cooler.toString())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_view, container, false)

        recyclerViewAdmin = view.findViewById(R.id.recyclerViewAdmin)
        recyclerViewAdmin.layoutManager = LinearLayoutManager(requireContext())

        adminAdapter = admin_adapter(requireContext(), adminList) { item, position ->
            val adminCatalogFragment = Admin_catalog().apply {
                arguments = Bundle().apply {
                    putString("componentType", item.name) // Use item.name to get the component name
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, adminCatalogFragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerViewAdmin.adapter = adminAdapter

        view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.Add)
            .setOnClickListener { openAddDialog() }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            showExitConfirmationDialog()
        }

        return view
    }

    private fun openAddDialog() {
        if (isAdded) {
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.pc_parts_add, null)

            AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setPositiveButton("Save") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit the app?")
            .setPositiveButton("Yes") { dialog, _ ->
                requireActivity().finish()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
