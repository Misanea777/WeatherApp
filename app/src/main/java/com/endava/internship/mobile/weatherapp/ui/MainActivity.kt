package com.endava.internship.mobile.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.databinding.ActivityMainBinding
import com.endava.internship.mobile.weatherapp.utils.Screens
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
        supportActionBar?.hide()

        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val showBottomNav = Screens.values()
                .first { getString(it.label) == destination.label }.bottomNavVisible

            bottomNavigationBar.visibility = if (showBottomNav) View.VISIBLE else View.GONE
        }
    }
}
