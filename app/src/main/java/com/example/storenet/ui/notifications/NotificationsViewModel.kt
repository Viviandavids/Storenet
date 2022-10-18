package com.example.storenet.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.storenet.data.models.Notification
import com.example.storenet.data.repository.NotificationRepository

class NotificationsViewModel(application: Application) : AndroidViewModel(application){
    private val repo = NotificationRepository(application)


fun getAllNotification(): MutableList<Notification> {
    return repo.getAllNotifications()
    }
}