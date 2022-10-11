package com.example.storenet.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storenet.data.models.Products
import com.example.storenet.data.repository.CartRepository

class CartViewModel : ViewModel() {

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
}