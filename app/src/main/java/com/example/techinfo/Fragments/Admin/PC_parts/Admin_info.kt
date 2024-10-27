package com.example.techinfo.Fragments.Admin.PC_parts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.techinfo.R

class Admin_info : Fragment() {

    private lateinit var componentName: String
    private lateinit var componentDetails: String
    private lateinit var createdTime: String // Assuming you will receive created time
    private lateinit var updatedTime: String // Assuming you will receive updated time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve data from the arguments
        componentName = arguments?.getString("componentName") ?: "Unknown"
        componentDetails = arguments?.getString("componentDetails") ?: "No details available"
        createdTime = arguments?.getString("createdTime") ?: "Not available"
        updatedTime = arguments?.getString("updatedTime") ?: "Not available"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up UI elements
        val titleTextView: TextView = view.findViewById(R.id.articleTitleTextView)
        val contentTextView: TextView = view.findViewById(R.id.contentTextView)
        val createdTimeTextView: TextView = view.findViewById(R.id.createdTimeTextView)
        val updatedTimeTextView: TextView = view.findViewById(R.id.updatedTimeTextView)


        // Populate the UI with the component data
        titleTextView.text = componentName
        contentTextView.text = componentDetails
        createdTimeTextView.text = createdTime
        updatedTimeTextView.text = updatedTime


    }
}
