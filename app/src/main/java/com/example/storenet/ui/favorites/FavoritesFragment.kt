package com.example.storenet.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storenet.R
import com.example.storenet.databinding.FavoritesFragmentBinding
import com.example.storenet.ui.home.ProductsAdapter

class FavoritesFragment : Fragment() {
    private lateinit var fragmentFavoritesBinding: FavoritesFragmentBinding
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        fragmentFavoritesBinding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return fragmentFavoritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       viewModel.getAllFavoriteProducts().observe(viewLifecycleOwner){listOfIds ->
           viewModel.getProductFromIds(listOfIds).observe(viewLifecycleOwner){listOfProducts ->
               if (listOfProducts.isNotEmpty()){
                   fragmentFavoritesBinding.listOfFavorites.visibility = View.VISIBLE
                   fragmentFavoritesBinding.notFound.visibility = View.GONE

               fragmentFavoritesBinding.listOfFavorites.adapter = ProductsAdapter(requireContext(), listOfProducts, childFragmentManager)
               fragmentFavoritesBinding.listOfFavorites.layoutManager = GridLayoutManager(requireContext(), 2)
           }else{
               // Favourite is empty message
               fragmentFavoritesBinding.listOfFavorites.visibility = View.GONE
               fragmentFavoritesBinding.notFound.visibility = View.VISIBLE
           }}
       }
    }


}