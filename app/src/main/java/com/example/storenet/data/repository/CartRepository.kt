package com.example.storenet.data.repository

import com.example.storenet.data.models.Products

object CartRepository {
    private val selectedProducts = mutableMapOf<Products, Int>()

    fun addToCart(product: Products){
        selectedProducts.put(product, 1)
    }
    fun getQuantity(product: Products): Int{
        return selectedProducts[product]!!
    }

    fun increaseQuantity(product: Products){
        // Increment quantity
        var quantity: Int = selectedProducts[product]!!
        quantity++
        selectedProducts[product] = quantity
    }

    fun reduceQuantity(product: Products){
        var quantity: Int = selectedProducts[product]!!
        quantity--
        selectedProducts[product] = quantity
    }

    fun removeFromCart(product: Products){
        selectedProducts.remove(product)
    }

    fun getPrice(): Double{
        var price: Double = 0.0
        for(items in selectedProducts.keys){
            price += items.price
        }
        return price
    }

    fun getSelectedProducts(): Map<Products, Int>{
        return selectedProducts.toMap()
    }
}