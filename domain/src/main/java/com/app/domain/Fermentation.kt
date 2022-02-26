package com.app.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Fermentation(
    @SerializedName("temp")
    val temp: @RawValue Temp
): Parcelable