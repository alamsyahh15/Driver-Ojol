package com.udacoding.driverojol.ui.request.model

import com.google.gson.annotations.SerializedName

data class GeocodedWaypointsItem(

    @field:SerializedName("types")
    val types: List<String?>? = null,

    @field:SerializedName("geocoder_status")
    val geocoderStatus: String? = null,

    @field:SerializedName("place_id")
    val placeId: String? = null
)