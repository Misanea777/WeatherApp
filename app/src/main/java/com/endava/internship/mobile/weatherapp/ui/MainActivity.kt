package com.endava.internship.mobile.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.databinding.ActivityMainBinding
import com.endava.internship.mobile.weatherapp.utils.Constants
import com.endava.internship.mobile.weatherapp.utils.Screens
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setLightMode()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavBar()
    }

    private fun setLightMode() {
        if (LocalDateTime.now().hour in Constants.DAY_HOURS_RANGE) AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        ) else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun setBottomNavBar() {
        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
        supportActionBar?.hide()

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            val showBottomNav = Screens.values()
                .first { getString(it.label) == destination.label }.bottomNavVisible

            bottomNavigationBar.visibility = if (showBottomNav) View.VISIBLE else View.GONE
        }
    }
}
