package com.example.storenet.data.favorite_provider

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.lifecycle.MutableLiveData

class SharedPreferenceFavorite(context: Context): FavoriteProvider {
    // Created our shared Preference file
    private val favoriteStorage: SharedPreferences =
        context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
    // Create an editor instance
    private val editor: Editor = favoriteStorage.edit()
    // Create an instance of a live data
    private val liveData = MutableLiveData<List<String>>()

    override fun addFavorite(productId: String) {
       editor.putString(productId, productId)
        editor.commit()

        notifyObservers()
    }

    override fun removeFavorite(productId: String) {
       editor.remove(productId)
        editor.commit()

        notifyObservers()
    }

    override fun isFavorite(productId: String): Boolean {
       val item: String? = favoriteStorage.getString(productId, "")
        if(item.isNullOrEmpty()){
            return false
        }
        return true
    }

    override fun getFavoriteItems(): MutableLiveData<List<String>> {
        notifyObservers()
       return liveData
    }
    private fun notifyObservers(){
        liveData.value = favoriteStorage.all.keys.toList()
    }
}