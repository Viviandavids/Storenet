package com.example.storenet.ui.productDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.storenet.R
import com.example.storenet.data.models.Products
import com.example.storenet.databinding.FragmentProductDetailsListDialogBinding
import com.example.storenet.ui.favorites.FavoritesViewModel

class ProductDetailsFragment(val product: Products) : BottomSheetDialogFragment() {
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
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

           // save the id to sharedPreference
            productDetailsViewModel.saveToCart(product)

            // alert user that item has been added to cart
            Toast.makeText(requireContext(), "Saved to Cart", Toast.LENGTH_LONG).show()

            // Close Bottom sheet
            this.dismiss()
        }

        // set listener to favorite button
        binding.selectAsFavourite.setOnClickListener {
            showAppropriateFavoriteIcon()
        }
        // Show appropriate favorite icon
        if(favoritesViewModel.isFavorite(product.uid!!)){
            binding.selectAsFavourite
                .setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }


    private fun showAppropriateFavoriteIcon() {
        // Check if product exists in favorite datastore/provider
        if (favoritesViewModel.isFavorite(product.uid!!)) {
            // remove item from storage
            favoritesViewModel.removeFromFavorite(product.uid!!)
            // show icon is not selected
            binding.selectAsFavourite
                .setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            // add item to storage
            favoritesViewModel.addToFavorite(product.uid!!)
            // show icon as selected
            binding.selectAsFavourite
                .setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }
}

