package com.example.techinfo.Fragments.Admin.PC_parts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.Fragments.Admin.admin_adapter
import com.example.techinfo.Fragments.BuildPC.ComponentData
import com.example.techinfo.Fragments.BuildPC.component_data
import com.example.techinfo.R

class Admin_catalog : Fragment() {
    private var componentType: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var componentAdapter: admin_adapter

    private val componentsMap = mapOf(
        "CPU" to listOf(
            component_data("Intel", "Details about Intel CPU"),
            component_data("AMD", "Details about AMD CPU")
        ),
        "GPU" to listOf(
            component_data("NVIDIA", "Details about NVIDIA GPU"),
            component_data("AMD", "Details about AMD GPU")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentType = arguments?.getString("componentType")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewPartCatalog)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val componentList = componentsMap[componentType] ?: emptyList()
        componentAdapter = admin_adapter(requireContext(), componentList.map { it.name } as ArrayList<String>) { componentName, position ->
            val selectedComponent = componentList[position]
            val bundle = Bundle().apply {
                putString("componentName", selectedComponent.name)
                putString("componentDetails", selectedComponent.details)
            }

            val adminInfoFragment = Admin_info().apply {
                arguments = bundle
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, adminInfoFragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = componentAdapter
    }
}
