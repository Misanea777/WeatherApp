package com.endava.internship.mobile.weatherapp.ui.forecast.weekly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.repository.WeatherRepository
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.Resource
import com.endava.internship.mobile.weatherapp.data.model.forecast.Daily
import com.endava.internship.mobile.weatherapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyForecastViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _forecast: MutableLiveData<Resource<List<Daily>>> = MutableLiveData()
    val forecast: LiveData<Resource<List<Daily>>> get() = _forecast

    fun getForecastForNextFiveDays() = viewModelScope.launch {
        _forecast.value = Resource.Loading
        _forecast.value = repository.getDailyForecast(Constants.LAT_LONG_CHISINAU, 5)
    }
}