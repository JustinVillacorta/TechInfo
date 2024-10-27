package com.example.techinfo.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.techinfo.MainNavigation.MainNavigation
import com.example.techinfo.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Initialize the VideoView
        val splashVideoView = view.findViewById<VideoView>(R.id.VideoView)

        // Set the video URI from the raw folder
        val videoUri: Uri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.splashintro)
        splashVideoView.setVideoURI(videoUri)

        // Start the video playback
        splashVideoView.start()

        // Add a listener for when the video completes
        splashVideoView.setOnCompletionListener {
            // Navigate to MainNavigation Activity
            val intent = Intent(activity, MainNavigation::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // Set a fallback timeout in case the video fails to play
        Handler(Looper.getMainLooper()).postDelayed({
            if (!splashVideoView.isPlaying) {
                val intent = Intent(activity, MainNavigation::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }, 1000) // Optional timeout

        return view
    }
}
