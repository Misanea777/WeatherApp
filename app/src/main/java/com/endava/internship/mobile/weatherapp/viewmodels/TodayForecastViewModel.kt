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
import org.joda.time.DateTimeZone
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class TodayForecastViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository
) : ViewModel() {

    val currentCity: String = getCityName()
    val localDate: String = getDate()

    private val _imageIcon = MutableLiveData<Int>()
    val imageIcon: LiveData<Int> = _imageIcon

    private val _isVisible = MutableLiveData<Boolean?>()
    val isVisible: LiveData<Boolean?> = _isVisible

    private val _currentTime = MutableLiveData<String>()
    var currentTime: LiveData<String> = _currentTime

    private val _hourlyData = MutableLiveData<List<Hourly>>()
    val hourlyData: LiveData<List<Hourly>> = _hourlyData

    private val _currentData = MutableLiveData<List<String>>()
    val currentData: LiveData<List<String>> = _currentData

    fun getCurrentWeather() = viewModelScope.launch {
        setTime(getDefaultTime())
        _isVisible.value = true
        val currentData = weatherRepository.getCurrentForecast((Constants.LAT_LONG_CHISINAU))
        if (currentData is Resource.Success) {
            setCurrentWeather(currentData.value.current)
            setTime(currentData.value.current?.dt)
            setImage(currentData.value.current)
        }
        _isVisible.value = false
    }

    fun getHourlyWeather() = viewModelScope.launch {
        _isVisible.value = true
        val hourlyData = weatherRepository.getHourlyForecast((Constants.LAT_LONG_CHISINAU))
        if (hourlyData is Resource.Success) setHourlyWeather(hourlyData.value)
        _isVisible.value = false
    }

    private fun setImage(currentTemp: Current?) {
        _imageIcon.value = currentTemp?.weather?.map { it.id }?.get(0)
    }

    private fun setCurrentWeather(currentTemp: Current?) {
        _currentData.value = listOf(
            currentTemp?.temp?.roundToInt().toString(),
            currentTemp?.wind_speed?.times(3.6)?.roundToInt().toString(),
            currentTemp?.humidity.toString()
        )
    }

    private fun setHourlyWeather(hourlyWeather: ForecastResponse) {
        _hourlyData.postValue(hourlyWeather.hourly?.toList())
    }

    private fun getCityName() = Constants.DEFAULT_CITY

    private fun setTime(currentTemp: Long?) {
        val dt = DateTimeZone.getDefault()
        _currentTime.value = DateTime(currentTemp?.times(1000), dt).toString(Constants.TIME_PATTERN)
    }

    private fun getDefaultTime() = DateTime().millis / 1000

    private fun getDate(): String =
        DateTime().dayOfWeek().asText + ", " +
                DateTime().monthOfYear().asText + " " +
                DateTime().dayOfMonth.toString()
}