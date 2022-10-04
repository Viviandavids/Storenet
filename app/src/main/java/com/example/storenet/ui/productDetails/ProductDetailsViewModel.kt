package com.example.storenet.ui.productDetails

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.storenet.data.models.Products
import com.example.storenet.data.repository.CartRepository

class ProductDetailsViewModel(): ViewModel() {

    fun saveToCart(products: Products){
        CartRepository.addToCart(products)

    }
}