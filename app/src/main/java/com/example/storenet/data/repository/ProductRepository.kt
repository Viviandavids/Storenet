package com.example.storenet.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.storenet.data.firebase.ProductDatasource
import com.example.storenet.data.models.Products

class ProductRepository {
    fun getProducts(): MutableLiveData<List<Products>>{
        val productDatasource = ProductDatasource()

        return productDatasource.getProductsInfo()
    }
}