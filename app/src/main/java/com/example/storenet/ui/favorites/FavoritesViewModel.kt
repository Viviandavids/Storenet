package com.example.storenet.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.storenet.data.models.Products
import com.example.storenet.data.repository.FavoriteRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
  private val favoriteRepository = FavoriteRepository(application)

    fun isFavorite(productId: String): Boolean{
        return favoriteRepository.isFavorite(productId)
    }

    fun removeFromFavorite(uid: String) {
        favoriteRepository.removeProduct(uid)
    }

  fun addToFavorite(uid: String) {
        favoriteRepository.addProduct(uid)
  }
    fun getAllFavoriteProducts(): MutableLiveData<List<String>>{
        return favoriteRepository.getAllFavorites()
    }
    fun getProductFromIds(listOfIds: List<String>): MutableLiveData<List<Products>>{
        return favoriteRepository.getProductFromIds(listOfIds)
    }
}