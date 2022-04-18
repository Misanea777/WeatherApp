package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository.WeatherRepository
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.data.model.forecast.Current
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.model.forecast.Hourly
import com.endava.internship.mobile.weatherapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class TodayForecastViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository,
    private var time: DateTime
) : ViewModel() {

    var currentCity: String = defaultCityName()

    val currentTime: String = getTime()

    val localDate: String = getDate()

    private val _hourlyWeather = MutableLiveData<Resource<ForecastResponse>>()
    val hourlyWeather: LiveData<Resource<ForecastResponse>> = _hourlyWeather

    private val _currentWeather = MutableLiveData<Resource<Current>>()
    val currentWeather: LiveData<Resource<Current>> = _currentWeather

    fun getCurrentWeather() = viewModelScope.launch {
        _currentWeather.value = Resource.Loading
        _currentWeather.value = weatherRepository.getCurrentForecast((Constants.LAT_LONG_CHISINAU))
    }

    fun getHourlyWeather() = viewModelScope.launch {
        _hourlyWeather.value = Resource.Loading
        _hourlyWeather.value = weatherRepository.getHourlyForecast((Constants.LAT_LONG_CHISINAU))
    }

    private fun defaultCityName(): String {
        return "Chisinau"
    }

    private fun getTime(): String = time.toString("hh:mm")

    private fun getDate(): String =
        time.dayOfWeek().asText + ", " +
                time.monthOfYear().asText + " " +
                time.dayOfMonth.toString()
}