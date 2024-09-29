package com.example.weatherapp.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class CityData(@SerializedName("weather") val weathers: List<Weather>?,
                    @SerializedName("main") val temperatureData: TemperatureData,
                    val name: String) : Parcelable
