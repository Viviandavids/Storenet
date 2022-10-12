package com.example.storenet.data.favorite_provider

interface FavoriteProvider {
    fun addFavorite(productId: String)

    fun removeFavorite(productId: String)

    fun isFavorite(productId: String): Boolean

    fun getFavoriteItems(): List<String>
}