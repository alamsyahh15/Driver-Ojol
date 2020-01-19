package com.udacoding.driverojol

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@Suppress("DEPRECATION")
@SuppressLint("Registered")
class MyFirebaseMessagingServices : FirebaseMessagingService() {
    val TAG = "Service"
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        Log.d(TAG, "From: " + remoteMessage!!.from)
        Log.d(TAG, "Notification Message Body: " +
                remoteMessage.notification?.body)

        showNotification()

    }

    @SuppressLint("WrongConstant")
    private fun showNotification() {

        val notificationBuilder = NotificationCompat.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pageIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                Intent.FLAG_ACTIVITY_NEW_TASK
            )

        val defaultSoundUri = RingtoneManager
            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        notificationBuilder
            .setSmallIcon(R.drawable.logo)
            .setBadgeIconType(R.drawable.logo)
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo))
            .setContentIntent(pageIntent)
            .setContentTitle("New Order,yuk Ambil")
            .setContentText("Silahkan check list")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())

    }
}

