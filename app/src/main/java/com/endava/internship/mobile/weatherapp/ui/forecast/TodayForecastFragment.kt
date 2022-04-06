package com.endava.internship.mobile.weatherapp.ui.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.endava.internship.mobile.weatherapp.databinding.FragmentTodayForecastBinding

class TodayForecastFragment : Fragment() {

    private lateinit var binding: FragmentTodayForecastBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayForecastBinding.inflate(layoutInflater)
        return binding.root
    }
}