package com.endava.internship.mobile.weatherapp.ui.forecast

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.adapter.HourlyForecastAdapter
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels.TodayForecastViewModel
import com.endava.internship.mobile.weatherapp.data.model.forecast.Current
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.data.model.forecast.Temp
import com.endava.internship.mobile.weatherapp.databinding.FragmentTodayForecastBinding
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
        showCurrentWeather()

        sharedTodayViewModel.getHourlyWeather()
        sharedTodayViewModel.hourlyWeather.observe(viewLifecycleOwner) {
            if (it is Resource.Success) initHourlyAdapter(it.value.hourly!!)
            else Toast.makeText(
                this.requireContext(),
                "current weather doesn't exist",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initHourlyAdapter(hourlyData: List<Hourly>) {
        binding.weatherForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyForecastAdapter(hourlyData)
        }
    }

    private fun weatherCurrentToast() =
        Toast.makeText(requireContext(), "Error bindCurrentData", Toast.LENGTH_SHORT).show()

    private fun bindCurrentData(resource: Resource.Success<Current>) {
        setTemp(resource.value.temp?.roundToInt().toString())
        setWind(resource.value.wind_speed?.roundToInt().toString())
        setHumidity(resource.value.humidity.toString())
        resource.value.weather?.map { setWeatherIcon(weatherIDToResourceID(it.id!!)) }
    }

    private fun setTemp(temp: String) {
        binding.tempValue.text = context?.getString(R.string.temp_value, temp)
    }

    private fun setWind(wind: String) {
        binding.windValue.text = context?.getString(R.string.wind_value, wind)
    }

    private fun setHumidity(humidityValue: String) {
        binding.humidityValue.text = context?.getString(R.string.humidity_value, humidityValue + "%")
    }

    private fun setWeatherIcon(idIcon: Int) {
        binding.sunImage.setImageResource(weatherIDToResourceID(idIcon))
    }

    private fun showCurrentWeather() {
        sharedTodayViewModel.apply {
            getCurrentWeather()
            currentWeather.observe(viewLifecycleOwner) {
                if (it is Resource.Success) bindCurrentData(it) else weatherCurrentToast()
            }
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