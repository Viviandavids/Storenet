package com.example.storenet.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.storenet.R
import com.example.storenet.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize viewModel
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        // show subtotal, shippingCost and total
        val subtotal: Double = cartViewModel.getPrice()
        val shippingCost: Double = (0.02) * subtotal
        val total: Double = subtotal + shippingCost

        binding.subtotalCost.text = "$$subtotal"
        binding.shpCost.text = "$$shippingCost"
        binding.totalCost.text = "$$total"

        // Setup spinner showing card providers

    }
}