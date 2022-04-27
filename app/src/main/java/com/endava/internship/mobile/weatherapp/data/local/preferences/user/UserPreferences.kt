package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.preferences.user

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val email: Flow<String?>
    suspend fun saveEmail(email: String)

    val password: Flow<String?>
    suspend fun savePassword(password: String)
}