package com.udacoding.driverojol.ui.request.model

import com.google.gson.annotations.SerializedName

data class Bounds(

    @field:SerializedName("southwest")
    val southwest: Southwest? = null,

    @field:SerializedName("northeast")
    val northeast: Northeast? = null
)