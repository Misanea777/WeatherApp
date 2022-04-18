package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.joda.time.DateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TimeModule {

    @Singleton
    @Provides
    fun provideDateAndTime(): DateTime = DateTime()
}