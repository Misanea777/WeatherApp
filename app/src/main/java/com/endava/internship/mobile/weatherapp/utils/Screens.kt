package com.endava.internship.mobile.weatherapp.utils

import androidx.annotation.StringRes
import com.endava.internship.mobile.weatherapp.R

enum class Screens(@StringRes val label: Int, val bottomNavVisible: Boolean) {
    SPLASH(R.string.splashScreenLabel, false),
    TODAY(R.string.todayForecastLabel, true),
    WEEKLY(R.string.weeklyForecastLabel, true),
    SETTINGS(R.string.settingsFragmentLabel, true);
}