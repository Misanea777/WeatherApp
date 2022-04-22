package com.endava.internship.mobile.weatherapp.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val MAX_VALUE: Int = 100
private const val DELAY_TIME: Long = 10

class SplashViewModel : ViewModel() {

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> = _progress

    private val _loaded = MutableLiveData(false)
    val loaded: LiveData<Boolean> = _loaded

    private suspend fun updateProgress(i: Int) {
        _progress.value = i

        if (i < MAX_VALUE) {
            delay(DELAY_TIME)
            updateProgress(i + 1)
        } else {
            _loaded.value = true
            viewModelScope.coroutineContext.cancel()
        }
    }

    fun load() {
        viewModelScope.launch {
            updateProgress(0)
        }
    }
}
