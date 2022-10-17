package com.example.storenet.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.storenet.data.favorite_provider.FavoriteProvider
import com.example.storenet.data.favorite_provider.SharedPreferenceFavorite
import com.example.storenet.data.firebase.ProductDatasource
import com.example.storenet.data.models.Products

class FavoriteRepository(context: Context) {
    private val favoriteProvider: FavoriteProvider = SharedPreferenceFavorite(context)
    private val productDatasource = ProductDatasource()

    fun isFavorite(productId: String): Boolean{
        return favoriteProvider.isFavorite(productId)
    }

    fun removeProduct(uid: String) {
        favoriteProvider.removeFavorite(uid)
    }

    fun addProduct(uid: String) {
        favoriteProvider.addFavorite(uid)
    }
    fun getAllFavorites(): MutableLiveData<List<String>>{
        return favoriteProvider.getFavoriteItems()
    }

    fun getProductFromIds(listOfIds: List<String>): MutableLiveData<List<Products>>{
        return productDatasource.getProductFromIds(listOfIds)
    }
}