package com.endava.internship.mobile.weatherapp.ui.splashscreen

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.databinding.FragmentSplashBinding
import com.endava.internship.mobile.weatherapp.utils.Constants
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val splashViewModel: SplashViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = splashViewModel
        }

        splashViewModel.loaded.observe(viewLifecycleOwner) {
            if (it == true) {
                goToMainScreen()
            }
        }

        setRemoteConfig()
        splashViewModel.load()

        return binding.root
    }

    private fun goToMainScreen() {
        findNavController().navigate(R.id.action_splashFragment_to_today_forecast_fragment)
    }

    private fun setRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = Constants.MINIMUM_FETCH_INTERVAL
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        activity?.let {
            remoteConfig.fetchAndActivate().addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Config params updated")
                } else {
                    Log.d(TAG, "Fetch failed")
                }
            }
        }
    }
}