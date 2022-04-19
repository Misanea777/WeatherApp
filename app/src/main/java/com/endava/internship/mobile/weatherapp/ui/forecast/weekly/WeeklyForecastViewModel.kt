package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository.WeatherRepository
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.forecast.weekly.DayForecast
import com.endava.internship.mobile.weatherapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyForecastViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _forecast: MutableLiveData<List<DayForecast>> = MutableLiveData()
    val forecast: LiveData<List<DayForecast>> = _forecast

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDailyForecast() = viewModelScope.launch {
        _isLoading.value = true
        val response = repository.getDailyForecastFromLatLong(
            Constants.LAT_LONG_CHISINAU,
            Constants.EXPECTED_DAILY_FORECAST_DAYS
        )
        when (response) {
            is Resource.Success ->
                _forecast.value =
                    response.value.map { DayForecast(it.dateTime, it.temp.max, it.weather[0].id) }
        }
        _isLoading.value = false
    }
}