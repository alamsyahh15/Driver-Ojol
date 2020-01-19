package com.udacoding.driverojol.ui.request.model

import com.google.gson.annotations.SerializedName

data class Polyline(

    @field:SerializedName("points")
    val points: String? = null
)