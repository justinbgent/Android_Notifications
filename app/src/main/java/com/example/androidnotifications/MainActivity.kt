package com.example.androidnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOTIFICATION_ID = 54
        const val NOTIFICATION_ID2 = 545
        const val STRING_KEY = "STRING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "Basic Notification"

        btn_notif.setOnClickListener {
            var intent = Intent(this, FullscreenActivity::class.java)
            intent.putExtra(STRING_KEY, "Notification Tapped")
            val eventualIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            //this whole if statement is specifically for the Notification Channel, which is only in newer versions. What if I don't do this part?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name = "Basic Notification Channel"
                val notifChannel = NotificationChannel(channelID, name, NotificationManager.IMPORTANCE_DEFAULT)
                notifChannel.description = "I want a description"
                notifManager.createNotificationChannel(notifChannel)
            }

            val notifBuilder = NotificationCompat.Builder(this, channelID)
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                .setContentTitle("Default Notification")
                .setContentText("What a default notification looks like.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .setDefaults(Notification.DEFAULT_ALL) //sets sounds/vibration/light upon notification
                .setAutoCancel(true)
                .setContentIntent(eventualIntent)
            notifManager.notify(NOTIFICATION_ID, notifBuilder.build())
        }

        val notifManager2 = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID2 = "Max Notification"

        btn_two.setOnClickListener {
            var intent2 = Intent(this, FullscreenActivity::class.java)
            intent2.putExtra(STRING_KEY, "Second Button Pressed")
            val eventualIntent2 = PendingIntent.getActivity(this, 1, intent2, PendingIntent.FLAG_ONE_SHOT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name = "Max Notification Channel"
                val notifChannel = NotificationChannel(channelID2, name, NotificationManager.IMPORTANCE_MAX)
                notifChannel.description = "I want a 2nd description"
                notifManager2.createNotificationChannel(notifChannel)
            }

            val notifBuilder = NotificationCompat.Builder(this, channelID2)
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .setContentTitle("Max Notification")
                .setContentText("What a max notification looks like.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setDefaults(Notification.DEFAULT_ALL) //setDefaults, sets sounds/vibration/light upon notification
                .setAutoCancel(true)
                .setContentIntent(eventualIntent2)
            notifManager2.notify(NOTIFICATION_ID2, notifBuilder.build())
        }
    }
}
