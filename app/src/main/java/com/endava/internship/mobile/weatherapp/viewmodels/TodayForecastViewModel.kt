package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository.WeatherRepository
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.LatLong
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.data.model.forecast.ForecastResponse
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodayForecastViewModel @Inject constructor(
        private var weatherApi: WeatherApi
    ): ViewModel() {

    private val _currentCity = MutableLiveData<String>()
    var currentCity: LiveData<String> = _currentCity

    val currentTime: String = setTime()

    val localDate: String = setCurrentDate()

    private val _currentWeather = MutableLiveData<ForecastResponse>()
    val currentWeather: LiveData<ForecastResponse> = _currentWeather

    private suspend fun setForecastWeather() {
        _currentWeather.value = weatherApi.getWeatherDataFromLatLong(47.0105,28.8638)
    }

    // default local city is Chisinau
    private suspend fun setCityName() {
        //weatherRepository.getDailyForecast(LatLong(47.0105, 28.8638),1)
    }

    private fun setTime(): String {
        val localTime = Calendar.getInstance()
        val formattedTime = SimpleDateFormat("h:mm", Locale.getDefault())
        return formattedTime.format(localTime.time)
    }

    private fun setCurrentDate(): String {
        val localDate = Calendar.getInstance().time
        val formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(localDate)
        val splitDate = formattedDate.split(",")
        return splitDate[0] + "," + splitDate[1]
    }

    private fun setCurrentHourForecast() {

    }

    private fun setCurrentWeatherIcon() {

    }

    private fun setCurrentForecastData() {

    }

    private fun setDataInRecyclerView() {

    }
}