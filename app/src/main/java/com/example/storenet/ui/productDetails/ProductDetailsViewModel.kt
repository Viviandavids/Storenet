package com.example.storenet.ui.productDetails

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

class ProductDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val context: Context = application.applicationContext

    fun saveToCart(uid: String){
        // access the sharedPreference
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("Cart", Context.MODE_PRIVATE)

        // get access to the editor
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        // save the uid
        editor.putString(uid, "")
    }
}