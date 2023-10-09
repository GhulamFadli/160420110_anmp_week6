package com.example.a160420110_anmp_w4.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.a160420110_anmp_w4.R

fun createNotificationChannel(context: Context, priority: Int, showBadge:
Boolean, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.app_name)
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, priority)
        channel.description = description
        channel.setShowBadge(showBadge)
        val notificationManager =
            context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

}
