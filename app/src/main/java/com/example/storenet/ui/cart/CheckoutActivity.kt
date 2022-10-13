package com.example.storenet.ui.cart

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.storenet.R
import com.example.storenet.databinding.ActivityCheckoutBinding
import java.util.Calendar

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
        showOrderInfo()

        // Setup spinner showing card providers
        setupSpinner()


        binding.makePayment.setOnClickListener{
            if(inputIsValid()){
            showPaymentSuccessDialog()
                cartViewModel.clearCart()
            }
        }
    }

    private fun inputIsValid(): Boolean {
        val cardNumber = binding.cardNumber.text.toString()
        val cvv = binding.cvv.text.toString()

        if(cardNumber.length < 15){
            Toast.makeText(this, "Invalid card number", Toast.LENGTH_LONG).show()
            return false
        } else if (cardNumber.length > 16){
            Toast.makeText(this, "Invalid card number", Toast.LENGTH_LONG).show()
            return false
        } else if(cvv.length != 3){
            Toast.makeText(this, "Invalid cvv", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun showPaymentSuccessDialog() {
        AlertDialog.Builder(this)
            .setView(R.layout.layout_payment_successful)
            .setNegativeButton("Ok") { _, _ -> this@CheckoutActivity.finish() }
            .show()
    }

    private fun setupSpinner() {
        val listOfCardProviders = listOf<String>("Visa", "MasterCard", "Verve", "American Express")
        binding.cardName.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOfCardProviders
        )
    }

    private fun showOrderInfo() {
        val subtotal: Double = cartViewModel.getPrice()
        val shippingCost: Double = (0.01) * subtotal
        val total: Double = subtotal + shippingCost

        binding.subtotalCost.text = "$$subtotal"
        binding.shpCost.text = "$$shippingCost"
        binding.totalCost.text = "$$total"
    }
}