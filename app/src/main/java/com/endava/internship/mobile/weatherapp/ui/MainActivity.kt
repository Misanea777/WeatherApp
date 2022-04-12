package com.endava.internship.mobile.weatherapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.network.InterceptorWeatherAppId
import com.endava.internship.mobile.weatherapp.data.remote.WeatherApi
import com.endava.internship.mobile.weatherapp.databinding.ActivityMainBinding
import com.endava.internship.mobile.weatherapp.di.NetworkModule
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Url
import java.net.URL
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
        supportActionBar?.hide()

        lifecycleScope.launchWhenResumed {
            val test = NetworkModule().provideApiService(
                NetworkModule().provideRetrofit(
                    NetworkModule()
                        .provideGson(), NetworkModule()
                        .provideOkHttpClient(
                            InterceptorWeatherAppId(), HttpLoggingInterceptor()
                        )
                ))
            toast(test)
        }

    }

    suspend fun toast(weatherApi: WeatherApi) {
        val currentWeatherApi = weatherApi.getWeatherDataFromCity("Chisinau")
        Log.i("tset", currentWeatherApi.body().toString())
    }
}