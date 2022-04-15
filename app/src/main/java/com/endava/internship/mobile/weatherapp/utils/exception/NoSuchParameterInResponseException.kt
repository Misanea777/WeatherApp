package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.exception

class NoSuchParameterInResponseException : Exception{
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}