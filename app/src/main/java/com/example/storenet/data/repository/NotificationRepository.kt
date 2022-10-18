package com.example.storenet.data.repository

import android.content.Context
import com.example.storenet.data.models.Notification
import com.example.storenet.data.notification_provider.NotificationFromSharedPreference

class NotificationRepository(context: Context) {
    private val notificationProvider = NotificationFromSharedPreference(context)

    fun getAllNotifications(): MutableList<Notification>{
        return notificationProvider.getAllNotifications()
    }

    fun deleteNotification(notification: Notification){
        notificationProvider.deleteNotification(notification)
    }
}
