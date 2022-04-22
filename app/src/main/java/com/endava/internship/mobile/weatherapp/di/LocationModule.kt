package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.di

import android.content.Context
import android.location.Geocoder
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocationModule {

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext appContext: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(appContext)

    @Singleton
    @Provides
    fun provideGeocoder(@ApplicationContext appContext: Context): Geocoder =
        Geocoder(appContext, Locale.getDefault())

    @Singleton
    @Provides
    fun provideLocationDataSource(
        fusedLocationProviderClient: FusedLocationProviderClient,
        geocoder: Geocoder,
        @ApplicationContext appContext: Context
    ): LocationDataSource = LocationDataSource(fusedLocationProviderClient, geocoder, appContext)
}