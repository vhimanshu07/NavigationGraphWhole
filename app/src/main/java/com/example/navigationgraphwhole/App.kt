package com.example.navigationgraphwhole

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created by Himanshu Verma on 31/05/24.
 **/
const val CHANNEL_ID = "id"

class App : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val channel =
            NotificationChannel(CHANNEL_ID, "Example", NotificationManager.IMPORTANCE_DEFAULT)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannels(
            listOf(channel)
        )
    }
}