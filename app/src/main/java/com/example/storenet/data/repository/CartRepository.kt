package com.example.storenet.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.storenet.data.models.Products

object CartRepository {
    private val selectedProducts = mutableMapOf<Products, Int>()
    private val cartLiveData = MutableLiveData<MutableMap<Products, Int>>(selectedProducts)

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

       notifyValueChange()
    }

    fun decreaseQuantity(product: Products){
        var quantity: Int = selectedProducts[product]!!
        quantity--
        selectedProducts[product] = quantity

        notifyValueChange()
    }

    private fun notifyValueChange() {
        cartLiveData.value = selectedProducts
    }

    fun removeFromCart(product: Products){
        notifyValueChange()
        selectedProducts.remove(product)
    }

    fun getPrice(): Double{
        var price: Double = 0.0
        for(items in selectedProducts.keys){
            val totalPrice = items.price * selectedProducts[items]!!
            price += totalPrice
        }
        return price
    }

    fun getSelectedProducts(): Map<Products, Int>{
        return selectedProducts.toMap()
    }
    fun getCartLiveData(): MutableLiveData<MutableMap<Products, Int>>{
        return cartLiveData
    }
}