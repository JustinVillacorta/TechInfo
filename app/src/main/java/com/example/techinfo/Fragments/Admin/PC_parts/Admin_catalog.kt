package com.example.techinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techinfo.R
import com.example.techinfo.api_connector.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Admin_catalog : Fragment() {
    private var componentType: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var componentAdapter: admin_adapter

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

        fetchComponents(componentType)
    }

    private fun fetchComponents(componentType: String?) {
        val apiService = RetrofitInstance.getApiService()

        val call: Call<List<out Component>>? = when (componentType) {
            "CPU" -> apiService.getProcessors() as Call<List<out Component>>
            "GPU" -> apiService.getGpus() as Call<List<out Component>>
            "RAM" -> apiService.getRams() as Call<List<out Component>>
            "Motherboard" -> apiService.getMotherboards() as Call<List<out Component>>
            "PSU" -> apiService.getPowerSupplyUnits() as Call<List<out Component>>
            "Case" -> apiService.getComputerCases() as Call<List<out Component>>
            "CPU Cooler" -> apiService.getCpuCoolers() as Call<List<out Component>>
            "SSD" -> apiService.getSsds() as Call<List<out Component>>
            "HDD" -> apiService.getHdds() as Call<List<out Component>>
            else -> null
        }

        call?.enqueue(object : Callback<List<out Component>> {
            override fun onResponse(call: Call<List<out Component>>, response: Response<List<out Component>>) {
                if (response.isSuccessful) {
                    val componentList = response.body()?.map { item ->
                        when (item) {
                            is Processor -> ComponentItem(item.processor_name, item.processor_id)
                            is Gpu -> ComponentItem(item.gpu_name, item.gpu_id.toString())
                            is Ram -> ComponentItem(item.ram_name, item.ram_id.toString())
                            is Motherboard -> ComponentItem(item.motherboard_name, item.motherboard_id.toString())
                            is PowerSupplyUnit -> ComponentItem(item.psu_name, item.psu_id.toString())
                            is Case -> ComponentItem(item.case_name, item.case_id.toString())
                            is CpuCooler -> ComponentItem(item.cooler_name, item.cooler_id.toString())
                            is Ssd -> ComponentItem(item.ssd_name, item.ssd_id.toString())
                            is Hdd -> ComponentItem(item.hdd_name, item.hdd_id.toString())
                            else -> null
                        }
                    }?.filterNotNull() ?: emptyList()

                    // Initialize the adapter with the component list
                    componentAdapter = admin_adapter(requireContext(), ArrayList(componentList)) { componentItem, position ->
                        // Create an instance of Admin_info
                        val fragment = Admin_info().apply {
                            arguments = Bundle().apply {
                                putString("componentName", componentItem.name)
                                putString("componentDetails", "Details of ${componentItem.name} can be passed here.")
                                putString("createdTime", "2023-10-27") // Replace with actual creation time if available
                                putString("updatedTime", "2023-10-28") // Replace with actual update time if available
                            }
                        }


                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment) // Use this if using FragmentContainerView
                            .addToBackStack(null)
                            .commit()

                    }

                    recyclerView.adapter = componentAdapter
                } else {
                    Toast.makeText(requireContext(), "Failed to load components", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<out Component>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
