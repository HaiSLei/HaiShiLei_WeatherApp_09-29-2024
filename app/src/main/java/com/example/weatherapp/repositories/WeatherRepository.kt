package com.example.weatherapp.repositories

import android.util.Log
import com.example.weatherapp.api.ApiService
import com.example.weatherapp.models.CityData
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object WeatherRepository{

    private val api by lazy {
        ApiService.sharedInstance
    }

    suspend fun getWeather(city: String): Response<CityData> {
        if (city.isEmpty() || city.isBlank() ) {
            return Response.error(901, "city is empty".toResponseBody())
        }
        return api.getWeather(city)
    }

    suspend fun getWeather(lat: Double, long: Double): Response<CityData> {
        if (lat > 90 || lat < -90 || long > 180 || long < -180 ) {
            return Response.error(901, "lat and long are not in range".toResponseBody())
        }
        return api.getWeather(lat,long)
    }

}