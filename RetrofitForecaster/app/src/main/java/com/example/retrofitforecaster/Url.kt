package com.example.retrofitforecaster

object Url {
    private val url = "https://api.openweathermap.org/data/2.5/"
    val service: r_Services
    get() = r_Client.getClient(url).create(r_Services::class.java)
}