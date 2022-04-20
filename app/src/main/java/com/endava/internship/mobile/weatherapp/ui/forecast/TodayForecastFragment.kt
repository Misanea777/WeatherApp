package com.endava.internship.mobile.weatherapp.ui.forecast

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter.HourlyForecastAdapter
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels.TodayForecastViewModel
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.databinding.FragmentTodayForecastBinding
import com.endava.internship.mobile.weatherapp.utils.Constants
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class TodayForecastFragment : Fragment() {

    private lateinit var binding: FragmentTodayForecastBinding

    private val sharedTodayViewModel: TodayForecastViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            lifecycleOwner = viewLifecycleOwner

            viewTodayModel = sharedTodayViewModel

            todayFragment = this@TodayForecastFragment
        }

        sharedTodayViewModel.apply {
            getCurrentWeather()
            getHourlyWeather()
            hourlyData.observe(viewLifecycleOwner) {
                initHourlyAdapter(sharedTodayViewModel.hourlyData.value)
            }
        }
    }

    private fun initHourlyAdapter(hourlyData: List<Hourly>?) {
        binding.weatherForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyForecastAdapter(hourlyData)
        }
    }

    fun setSpannedTime(time: String): SpannableString {
        val spannableTime = SpannableString(time)
        spannableTime.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableTime
    }
}