package com.example.storenet.ui.home

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storenet.R
import com.example.storenet.data.models.Products
import com.example.storenet.ui.productDetails.ProductDetailsFragment

class ProductsAdapter(
    private val context: Context,
    private val listOfProducts: List<Products>,
    private val fragmentManager: FragmentManager
    ): RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.layout_for_products, parent, false)
        return ProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        // show brand
        val products = listOfProducts[position]

        holder.brand.text = products.brand
        // show product price
        holder.price.text = "$${products.price}"
        // show Image
        val imageUrl = products.image
        Glide.with(context)
            .load(imageUrl)
            .into(holder.productImage)

        // set onClick listener to the item
        holder.itemView.setOnClickListener {
            ProductDetailsFragment().show(fragmentManager, "Tag")

        }
    }
        override fun getItemCount(): Int = listOfProducts.size
    }

class ProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val brand: TextView = itemView.findViewById(R.id.brand)
    val price: TextView = itemView.findViewById(R.id.price)
    val productImage: ImageView = itemView.findViewById(R.id.product_image)
}