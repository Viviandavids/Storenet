package com.example.storenet.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storenet.R
import com.example.storenet.data.models.Notification
import java.util.Date

class NotificationAdapter(val context: Context, val listOfNotification: MutableList<Notification>):
    RecyclerView.Adapter<NotificationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
       val view: View = LayoutInflater.from(context).inflate(R.layout.layout_notification, parent, false)
       return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = listOfNotification[position]

        // show date
        val date = Date(notification.time)
        holder.date.text = date.toString()

        // show message
        holder.message.text = notification.message
    }

    override fun getItemCount(): Int = listOfNotification.size
}
class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val date: TextView = itemView.findViewById(R.id.date)
    val message: TextView = itemView.findViewById(R.id.message)
}