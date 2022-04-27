package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.di

import android.content.Context
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.preferences.user.DefaultUserPreferences
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.preferences.user.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext appContext: Context): UserPreferences =
        DefaultUserPreferences(appContext)
}