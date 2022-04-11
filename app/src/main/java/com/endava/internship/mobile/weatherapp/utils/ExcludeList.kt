package com.endava.internship.mobile.weatherapp.utils

class ExcludeList(private val items: List<String>) {
    override fun toString(): String {
        return itemsString(items)
    }
}