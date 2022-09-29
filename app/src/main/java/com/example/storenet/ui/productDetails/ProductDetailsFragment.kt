package com.example.storenet.ui.productDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.storenet.data.models.Products
import com.example.storenet.databinding.FragmentProductDetailsListDialogBinding

class ProductDetailsFragment(val product: Products) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        binding = FragmentProductDetailsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Load details
        binding.productName.text = product.brand
        binding.productDescription.text = product.seller
        binding.productPrice.text = "$${product.price}"
        binding.size.text = product.size

        // Load image
        Glide.with(requireContext())
            .load(product.image)
            .into(binding.productImage)

        binding.addToCart.setOnClickListener{
            // get the product uid
            val uid:String = product.uid ?: ""

           // save the id to sharedPreference
            productDetailsViewModel.saveToCart(uid)

            // alert user that item has been added to cart
            Toast.makeText(requireContext(), "Saved to Cart", Toast.LENGTH_LONG).show()
            binding.addToCart.text = "Remove from Cart"

            // adds the item to Cart
        }
    }
}
