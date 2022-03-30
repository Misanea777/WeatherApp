package com.endava.internship.mobile.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.data.remote.RemoteDataSource
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}