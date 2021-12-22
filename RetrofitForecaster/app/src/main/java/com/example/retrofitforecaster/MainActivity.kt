package com.example.retrofitforecaster

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mService: r_Services // Отложенная инициализация, чтобы не делать проверки на null
    private lateinit var layoutManager: LinearLayoutManager
    val adapter = Adapter()
    var weather = listOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        title = "Astrakhan"
        mService = Url.service
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        getAllWeatherList("Astrakhan")
    }

    private fun getAllWeatherList(city: String) {
        mService.getWeatherList(city).enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val wth = response.body() as DataWeather
                weather = wth.list

                adapter.submitList(weather)
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
                Log.d("Message:", t.message.toString())
            }
        })
    }
}