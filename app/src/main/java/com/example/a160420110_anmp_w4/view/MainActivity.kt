package com.example.a160420110_anmp_w4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.a160420110_anmp_w4.R
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.example.a160420110_anmp_w4.util.createNotificationChannel


class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }
    companion object{
        private var instance:MainActivity ?= null
        fun showNotif(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${(instance?.getString(R.string.app_name))}"
            val notificationBuilder =
                NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
            val notificationManager =
                NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            notificationManager.notify(1001, notificationBuilder.build())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            "Notification channel for creating new student")
    }
}