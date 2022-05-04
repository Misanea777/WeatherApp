package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.preferences.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data_store")

class DefaultUserPreferences(context: Context) : UserPreferences {
    private val applicationContext = context.applicationContext

    override val email: Flow<String?>
        get() = applicationContext.dataStore.data.map { userDataStore ->
            userDataStore[EMAIL]
        }

    override suspend fun saveEmail(email: String) {
        applicationContext.dataStore.edit { userDataStore ->
            userDataStore[EMAIL] = email
        }
    }

    override val password: Flow<String?>
        get() = applicationContext.dataStore.data.map { userDataStore ->
            userDataStore[PASSWORD]
        }

    override suspend fun savePassword(password: String) {
        applicationContext.dataStore.edit { userDataStore ->
            userDataStore[PASSWORD] = password
        }
    }

    companion object{
        private val EMAIL = stringPreferencesKey("email")
        private val PASSWORD = stringPreferencesKey("password")
    }
}