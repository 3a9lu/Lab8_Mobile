package com.example.retrofitforecaster

import retrofit2.Call
import retrofit2.http.*

interface r_Services {
    @GET("forecast?&appid=066aefd96d5c8b466ce25793ffcaf6b2&units=metric&lang=ru")
    fun getWeatherList (@Query("q")name: String): Call<DataWeather> // Город
}