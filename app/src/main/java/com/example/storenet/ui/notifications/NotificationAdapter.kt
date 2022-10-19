package com.example.storenet.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.storenet.R
import com.example.storenet.data.models.Notification
import java.util.Date

class NotificationAdapter(
    val context: Context,
    private val listOfNotification: MutableList<Notification>,
    private val notificationsViewModel: NotificationsViewModel
    ):
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

        // setup popup menu
        holder.menuBtn.setOnClickListener {
            showPopupMenu(holder, notification)
        }

    }

    private fun showPopupMenu(holder: NotificationViewHolder, notification: Notification) {
        val popupMenu = PopupMenu(context, holder.menuBtn)
        popupMenu.inflate(R.menu.notification_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete -> {
                    notificationsViewModel.deleteNotification(notification)
                    Toast.makeText(context, "Delete Clicked", Toast.LENGTH_LONG).show()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }
    }

    override fun getItemCount(): Int = listOfNotification.size
}
class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val date: TextView = itemView.findViewById(R.id.date)
    val message: TextView = itemView.findViewById(R.id.message)
    val menuBtn: ImageButton = itemView.findViewById(R.id.show_menu)
}