package com.example.storenet.ui.cart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.storenet.R
import com.example.storenet.data.models.Products
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

        val products: List<Products> = cartViewModel.getProducts()

        binding.makePayment.setOnClickListener{
            if(inputIsValid()){
            showPaymentSuccessDialog()
                showNotification(
                    message = "You just paid for some items worth $${cartViewModel.getPrice()}"
                )
                cartViewModel.clearCart()
            }
        }
    }

    // show user payment notification
    private fun showNotification(message: String) {
        val id = "payment successful"
        // Create notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // Declare variables
            val channelName = "Payment Confirmation"
            val description = "User just made a successful payment"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT

            // Created the notification channel
            val notificationChannel = NotificationChannel("payment successful", channelName, importance)
            notificationChannel.description = description

            // Assign the channel to the device notification manager
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        // Create the
        val builder = NotificationCompat.Builder(this, id)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("Payment made")
        builder.setContentText(message)

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(12333, builder.build())

        saveNotification(message)

    }

    private fun saveNotification(message: String) {

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
            .setPositiveButton("Ok") { _, _ -> this@CheckoutActivity.finish() }
            .setCancelable(false)
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