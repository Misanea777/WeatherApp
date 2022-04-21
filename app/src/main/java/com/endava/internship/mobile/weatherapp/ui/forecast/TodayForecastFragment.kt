package com.endava.internship.mobile.weatherapp.ui.forecast

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter.HourlyForecastAdapter
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels.TodayForecastViewModel
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.databinding.FragmentTodayForecastBinding

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

            viewModel = sharedTodayViewModel

            todayFragment = this@TodayForecastFragment
        }

        sharedTodayViewModel.apply {
            getTime()
            getCurrentWeather()
            currentData.observe(viewLifecycleOwner) {
                setIcon(it[3].toInt())
                binding.weatherProgressBar.isVisible = isVisible.value == true
            }
            getHourlyWeather()
            hourlyData.observe(viewLifecycleOwner) {
                binding.weatherProgressBar.isVisible = isVisible.value == true
                initHourlyAdapter(hourlyData.value)
            }
        }
    }

    private fun setIcon(id: Int) {
        binding.sunImage.setImageResource(weatherIDToResourceID(id))
    }

    private fun initHourlyAdapter(hourlyData: List<Hourly>?) {
        binding.weatherForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyForecastAdapter(hourlyData, context)
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