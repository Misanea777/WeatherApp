package com.endava.internship.mobile.weatherapp.utils

import com.endava.internship.mobile.weatherapp.utils.itemsString

class ExcludeList(private val items: List<String>) {
    override fun toString(): String {
        return itemsString(items)
    }
}