package com.example.storenet.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storenet.data.models.Products
import com.example.storenet.data.repository.ProductRepository

class HomeViewModel : ViewModel() {
   private val productRepository = ProductRepository()
   private val products: MutableLiveData<List<Products>> = productRepository.getProducts()

    fun getAllProducts(): MutableLiveData<List<Products>>{
        return products
    }
}