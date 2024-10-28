package com.example.techinfo.MainNavigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.techinfo.Fragments.Bottleneck.BottleNeck
import com.example.techinfo.Fragments.BuildPC.BuildPC
import com.example.techinfo.Fragments.PcComparison.PcComparison
import com.example.techinfo.Fragments.Troubleshoot.troubleshoot_content.TroubleShoot
import com.example.techinfo.R
import com.google.android.material.navigation.NavigationView
import com.example.techinfo.databinding.ActivityMainNavigationBinding

class MainNavigation : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var binding: ActivityMainNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Initialize with BuildPCFragment and hide bottom navigation
        if (savedInstanceState == null) {
            replaceFragment(BuildPC(), "Build your PC")  // Use BuildPCFragment instead of BuildPC
            navigationView.setCheckedItem(R.id.nav_buildpc)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_buildpc -> {
                replaceFragment(BuildPC(),"Build your PC")  // Use BuildPCFragment instead of BuildPC
            }
            R.id.nav_pc_compare -> {
                replaceFragment(PcComparison(), "PC compare")
            }
            R.id.nav_bottleneck -> {
                replaceFragment(BottleNeck(), "Bottleneck Calculator")
            }
            R.id.nav_troubleshoot -> {
                replaceFragment(TroubleShoot(), "Troubleshoot")

            }


        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }




    private fun replaceFragment(fragment: Fragment, title: String) {
        // Clear all fragments from the back stack
        supportFragmentManager.popBackStackImmediate(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // Replace the fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Update the action bar title
        supportActionBar?.title = title
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
