package com.example.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val CHANNEL_ID = "notification_channel"
const val CHANNEL_NAME = "com.example.login"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.getNotification() != null){
            // Handle the notification payload

            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }
    }
    //Generate the notification

    //attach the notification with custom layout
    //show the notification
    fun getRemoteview(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("com.example.login", R.layout.notif)
        remoteView.setTextViewText(R.id.title, title)
        remoteView.setTextViewText(R.id.message, message)
//        remoteView.setImageViewResource(R.id.app_logo, R.drawable.logo_notif)
        return remoteView
    }


    fun generateNotification(title: String, message: String) {
        // Implementation for generating a notification
        val intent= Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // This could include creating a notification channel, building the notification,
        // and displaying it using NotificationManager.
        val pendingIntent= PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
        //channelId, ChannelName
        var builder: NotificationCompat.Builder= NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_notif)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
        builder = builder.setContent(getRemoteview(title, message))
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "Channel untuk notifikasi penting"
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
        builder.color = ContextCompat.getColor(this, R.color.purple_700)
        builder.setColorized(true)
        notificationManager.notify(0, builder.build())
    }

}