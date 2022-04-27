package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.validators

import com.endava.internship.mobile.weatherapp.utils.validators.LiveDataValidator

class LiveDataValidatorResolver(private val validators: List<Pair<LiveDataValidator, Boolean>>) {
    fun isValid(): Boolean =
        validators.fold(true) { isValid, item -> item.first.isValid(item.second) && isValid }
}