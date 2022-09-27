package com.example.storenet.data.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.storenet.data.models.Products
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProductDatasource {
    // Get reference to fireStore database
    private val db = Firebase.firestore

    // Create function that will fetch the products from the database
    fun getProductsInfo(): MutableLiveData<List<Products>> {
        val productLiveData = MutableLiveData<List<Products>>()

        db.collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val listOfProducts: List<Products> = documents.toObjects(Products::class.java)
                productLiveData.value = listOfProducts
            }
            .addOnFailureListener { error ->
                Log.e("Firebase Error", error.message.toString())
            }
        return productLiveData
    }
}