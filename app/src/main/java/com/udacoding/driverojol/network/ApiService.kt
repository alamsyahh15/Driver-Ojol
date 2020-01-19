package com.udacoding.driverojol.network

import com.udacoding.driverojol.ui.request.model.ResultRoute
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("json")
    fun actionRouter(@Query("origin") origin : String,
                     @Query("destination") destination: String,
                     @Query("key") key: String) : Flowable<ResultRoute>

    @Headers(
        "Authorization: key=AAAAatftHTA:APA91bH8et0bo2ExR0K7upJ0-Oe4974AJiItaH0bbL1HpXhWTkd5wAfdpWElUcA2wPmTZAIgZjc3i3JL2Ba37pMSWeR-JUv_ueRxZgYrp-daGriYcbqcoZvY6fC-Bo-q-lIOhLggMjmn",
        "Content-Type:application/json"
    )

    @POST("fcm/send")
    fun sendNotification(@Body requestNotofication : RequestNotification) : Call<ResponseBody>

}