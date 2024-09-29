package com.example.weatherapp.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class Weather(val id: String?,
                    val main: String?,
                    val description: String?,
                    val icon: String? ) : Parcelable

