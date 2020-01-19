package com.udacoding.driverojol.ui.request.model

import com.google.gson.annotations.SerializedName

data class Southwest(

    @field:SerializedName("lng")
    val lng: Double? = null,

    @field:SerializedName("lat")
    val lat: Double? = null
)