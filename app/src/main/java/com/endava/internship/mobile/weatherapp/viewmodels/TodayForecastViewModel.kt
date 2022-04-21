package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels

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
    private var weatherRepository: WeatherRepository
) : ViewModel() {

    private val _isVisible = MutableLiveData<Boolean?>()
    val isVisible: LiveData<Boolean?> = _isVisible

    val currentCity: String = getCityName()

    private val _currentTime = MutableLiveData<String>()
    var currentTime: LiveData<String> = _currentTime

    val localDate: String = getDate()

    private val _hourlyData = MutableLiveData<List<Hourly>>()
    val hourlyData: LiveData<List<Hourly>> = _hourlyData

    private val _currentData = MutableLiveData<List<String>>()
    val currentData: LiveData<List<String>> = _currentData

    fun getCurrentWeather() = viewModelScope.launch {
        val currentData = weatherRepository.getCurrentForecast((Constants.LAT_LONG_CHISINAU))
        if (currentData is Resource.Success) {
            setCurrentWeather(currentData.value.current)
            _isVisible.value = false
        } else {
            _isVisible.value = true
        }
    }

    fun getHourlyWeather() = viewModelScope.launch {
        val hourlyData = weatherRepository.getHourlyForecast((Constants.LAT_LONG_CHISINAU))
        if (hourlyData is Resource.Success) {
            _isVisible.value = false
            setHourlyWeather(hourlyData.value)
        } else {
            _isVisible.value = true
        }
    }

    private fun setCurrentWeather(currentTemp: Current?) {

        // I saved just like that because otherwise I need change in model "val" to "var"
        _currentData.value = listOf(
            currentTemp?.temp?.roundToInt().toString(),
            currentTemp?.wind_speed?.times(3.6)?.roundToInt().toString(),
            currentTemp?.humidity.toString(),
            currentTemp?.weather?.map { it.id }?.first().toString()
        )
    }

    private fun setHourlyWeather(hourlyWeather: ForecastResponse) {
        _hourlyData.postValue(hourlyWeather.hourly?.toList())
    }

    private fun getCityName() = Constants.DEFAULT_CITY

    fun getTime() {
        _currentTime.value = DateTime().toString(Constants.TIME_PATTERN)
    }

    private fun getDate(): String =
        DateTime().dayOfWeek().asText + ", " +
                DateTime().monthOfYear().asText + " " +
                DateTime().dayOfMonth.toString()
}