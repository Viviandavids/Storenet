package com.example.storenet.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storenet.data.models.Notification
import com.example.storenet.data.models.Products
import com.example.storenet.data.repository.CartRepository
import com.example.storenet.data.repository.NotificationRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val notificationRepo = NotificationRepository(application)

    fun getProducts(): List<Products>{
        return CartRepository.getSelectedProducts().keys.toList()
    }

    fun getQuantity(product: Products): Int{
        return CartRepository.getQuantity(product)
    }

    fun increaseQuantity(product: Products){
       CartRepository.increaseQuantity(product)
    }

    fun decreaseQuantity(product: Products){
       CartRepository.decreaseQuantity(product)
    }

    fun removeFromCart(product: Products){
       CartRepository.removeFromCart(product)
    }

    fun getCartLiveData(): MutableLiveData<MutableMap<Products, Int>> {
        return CartRepository.getCartLiveData()
    }
    fun getPrice(): Double{
        return CartRepository.getPrice()
    }
    fun clearCart(){
        CartRepository.clearCart()
    }
    fun saveNotification(notification: Notification){
        notificationRepo.saveNotification(notification)
    }
}