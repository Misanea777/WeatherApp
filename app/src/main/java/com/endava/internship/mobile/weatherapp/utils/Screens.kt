package com.endava.internship.mobile.weatherapp.utils

import androidx.annotation.StringRes
import com.endava.internship.mobile.weatherapp.R

enum class Screens(@StringRes val label: Int, val bottomNavVisible: Boolean) {
    LOGIN(R.string.login_fragment_label, false),
    SIGN_UP(R.string.sign_up_fragment_label, false),
    SPLASH(R.string.splash_fragment_label, false),
    TODAY(R.string.today_forecast_fragment_label, true),
    WEEKLY(R.string.weekly_forecast_fragment_label, true),
    SETTINGS(R.string.settings_fragment_label, true);
}