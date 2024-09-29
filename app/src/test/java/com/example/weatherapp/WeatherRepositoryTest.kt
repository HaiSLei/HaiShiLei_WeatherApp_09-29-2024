package com.example.weatherapp

import com.example.weatherapp.repositories.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherRepositoryTest {

    @Test
    fun verifyInvalidLatAndLongReturnFailure() {
        runBlocking{
            val weather = WeatherRepository.getWeather(-100.0,100.0)
            assert(!weather.isSuccessful)
        }
    }

    @Test
    fun verifyInvalidLatAndLongReturnSuccess() {
        runBlocking{
            val weather = WeatherRepository.getWeather(80.0,50.0)
            assert(weather.isSuccessful)
        }
    }


    @Test
    fun verifyBlankCityReturnFailure() {
        runBlocking{
            val weather = WeatherRepository.getWeather("    ")
            assert(!weather.isSuccessful)
        }
    }

    @Test
    fun verifyEmptyCityReturnFailure() {
        runBlocking{
            val weather = WeatherRepository.getWeather("")
            assert(!weather.isSuccessful)
        }
    }

    @Test
    fun verifyMisspellCityReturnFailure() {
        runBlocking{
            val weather = WeatherRepository.getWeather("NewYerk")
            assert(!weather.isSuccessful)
        }
    }

    @Test
    fun verifyCityReturnSuccessful() {
        runBlocking{
            val weather = WeatherRepository.getWeather("Boston")
            assert(weather.isSuccessful)
        }
    }




}