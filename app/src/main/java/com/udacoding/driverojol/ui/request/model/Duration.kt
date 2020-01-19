package com.udacoding.driverojol.ui.request.model

import com.google.gson.annotations.SerializedName

data class Duration(

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
)