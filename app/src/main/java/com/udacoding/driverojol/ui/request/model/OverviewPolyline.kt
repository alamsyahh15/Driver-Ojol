package com.udacoding.driverojol.ui.request.model

import com.google.gson.annotations.SerializedName

data class OverviewPolyline(

    @field:SerializedName("points")
    val points: String? = null
)