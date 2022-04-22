package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import com.endava.internship.mobile.weatherapp.utils.LatLong
import com.google.android.gms.location.FusedLocationProviderClient

class LocationDataSource(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val context: Context
) {
    fun getLastLocation(
        requestPermission: () -> Unit,
        locationCallback: (Location?) -> Unit
    ) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission()
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
            locationCallback(location)
        }
    }

    fun getCityName(latLong: LatLong): String =
        geocoder.getFromLocation(latLong.lat, latLong.long, 3)[0].locality
}