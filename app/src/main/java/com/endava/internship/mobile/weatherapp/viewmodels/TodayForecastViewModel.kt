package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository.WeatherRepository
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.data.model.forecast.Current
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class TodayForecastViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository,
    private var time: DateTime
) : ViewModel() {

    val currentCity: String = getCityName()

    val currentTime: String = getTime()

    val localDate: String = getDate()

    private val _currentTemp = MutableLiveData<String>()
    val currentTemp: LiveData<String> = _currentTemp

    private val _currentWindSpeed = MutableLiveData<String>()
    val currentWindSpeed: LiveData<String> = _currentWindSpeed

    private val _currentHumidity = MutableLiveData<String>()
    val currentHumidity: LiveData<String> = _currentHumidity

    private val _hourlyData = MutableLiveData<List<Hourly>>()
    val hourlyData: LiveData<List<Hourly>> = _hourlyData

    fun getCurrentWeather() = viewModelScope.launch {

        val currentData = weatherRepository.getCurrentForecast((Constants.LAT_LONG_CHISINAU))

        if (currentData is Resource.Success)
            setCurrentWeather(currentData.value.current)
        else
            Log.e("state weather", "something doesn't work => check current method")
    }

    fun getHourlyWeather() = viewModelScope.launch {

        val hourlyData = weatherRepository.getHourlyForecast((Constants.LAT_LONG_CHISINAU))

        if (hourlyData is Resource.Success)
            setHourlyWeather(hourlyData.value)
        else
            Log.e("state weather", "something doesn't work => check hourly method")
    }

    private fun setCurrentWeather(currentTemp: Current?) {
        _currentTemp.value = currentTemp?.temp?.roundToInt().toString()
        _currentWindSpeed.value = currentTemp?.wind_speed?.roundToInt().toString()
        _currentHumidity.value = currentTemp?.humidity?.toString()
    }

    private fun setHourlyWeather(hourlyWeather: ForecastResponse) {
        _hourlyData.postValue(hourlyWeather.hourly?.toList())
    }

    private fun getCityName() = Constants.DEFAULT_CITY

    private fun getTime(): String = time.toString("hh:mm")

    private fun getDate(): String =
        time.dayOfWeek().asText + ", " +
                time.monthOfYear().asText + " " +
                time.dayOfMonth.toString()
}