package com.example.weatherapp.ui

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.models.CityData
import com.example.weatherapp.repositories.WeatherRepository
import com.example.weatherapp.utilities.SharePreferenceUtilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class WeatherViewModel( application: Application) : AndroidViewModel(application) {

    private val _weatherResult: MutableLiveData<Response<CityData>> by lazy {
        MutableLiveData()
    }

    val weatherResult: LiveData<Response<CityData>> = _weatherResult

    // get sharePreference for storing last searched city
    private val sp =
        getApplication<Application>().getSharedPreferences("Weather", Context.MODE_PRIVATE)

    //get weather by city
    fun getWeather(city: String) {

        //launch in IO dispatcher to fetch the suspend weather api
        viewModelScope.launch(Dispatchers.IO) {
            // try and catch the weather api for issues. Using postValue(happens in main dispatcher) to set the _weatherResult
            try {
                val response = WeatherRepository.getWeather(city)
                _weatherResult.postValue(response)
            } catch(e: Exception) {
                val responseBody = e.message?.toResponseBody() ?: "unknown error".toResponseBody()
                Log.e(TAG, "getWeather: $responseBody")
                _weatherResult.postValue(Response.error(900, responseBody))
            }
        }
    }

    // get weather by latitude and longitude
    fun getWeather(lat : Double, long : Double) {

        //launch in IO dispatcher to fetch the suspend weather api
        viewModelScope.launch(Dispatchers.IO) {
            // try and catch the weather api for issues. Using postValue(happens in main dispatcher) to set the _weatherResult
            try {
                val response = WeatherRepository.getWeather(lat,long)
                _weatherResult.postValue(response)
            } catch(e: Exception) {
                val responseBody = e.message?.toResponseBody() ?: "unknown error".toResponseBody()
                Log.e(TAG, "getWeather: $responseBody")
                _weatherResult.postValue(Response.error(900, responseBody))
            }
        }
    }

    //set the last city searched in sharePreference
    fun setLastCity(city: String) {
        val spUtil = SharePreferenceUtilities()
        spUtil.setSp("city", city, sp)
    }

    //get the last city searched from sharePreference
    fun getLastCity() : String? {
        val spUtil = SharePreferenceUtilities()
        return spUtil.getSp("city", sp)
    }
}