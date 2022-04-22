package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local

import android.location.Geocoder
import com.endava.internship.mobile.weatherapp.utils.Constants
import com.endava.internship.mobile.weatherapp.utils.LatLong

class LocationDataSource(
    private val geocoder: Geocoder,
) {
    fun getCityName(latLong: LatLong): String =
        geocoder.getFromLocation(
            latLong.lat,
            latLong.long,
            Constants.MAX_LOCATION_RESULTS
        )[0].locality
}