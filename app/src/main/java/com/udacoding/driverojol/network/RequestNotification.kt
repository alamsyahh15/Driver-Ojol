package com.udacoding.driverojol.network

import com.google.gson.annotations.SerializedName
import com.udacoding.driverojol.ui.request.model.Booking

class RequestNotification {
    @SerializedName("to")
    var token: String ? =null

    @SerializedName("data")
    var sendNotification: Booking? =null
}