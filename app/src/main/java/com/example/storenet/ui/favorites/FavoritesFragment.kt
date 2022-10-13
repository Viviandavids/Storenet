package com.example.storenet.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storenet.R
import com.example.storenet.databinding.FavoritesFragmentBinding

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
           fragmentFavoritesBinding.listOfFavorites.layoutManager = LinearLayoutManager(requireContext())
           fragmentFavoritesBinding.listOfFavorites.adapter = FavoritesAdapter(requireContext())
       }
    }


}