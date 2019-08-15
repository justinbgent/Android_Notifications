package com.example.androidnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOTIFICATION_ID = 54
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "Basic Notification"

        btn_notif.setOnClickListener {
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
            notifManager.notify(NOTIFICATION_ID, notifBuilder.build())
        }
    }
}
