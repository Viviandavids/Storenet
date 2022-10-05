package com.example.storenet.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storenet.R
import com.example.storenet.data.models.Products
import com.google.android.material.button.MaterialButton


class CartAdapter(val context: Context, var cartViewModel: CartViewModel): RecyclerView.Adapter<CartViewHolder>(){
    val listOfSelectedProducts: List<Products> = cartViewModel.getProducts()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       val itemView = LayoutInflater.from(context).inflate(R.layout.layout_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product: Products = listOfSelectedProducts[position]
        // show name
        holder.productName.text = product.brand

        // show price
        holder.price.text = "$${product.price}"

        // show image
        Glide.with(context)
            .load(product.image)
            .into(holder.image)

        // show quantity
        holder.quantity.text = cartViewModel.getQuantity(product).toString()
    }

    override fun getItemCount(): Int = listOfSelectedProducts.size

}
class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val image: ImageView = itemView.findViewById(R.id.image)
    val productName: TextView = itemView.findViewById(R.id.product_name)
    val price: TextView = itemView.findViewById(R.id.price)
    val quantity: TextView = itemView.findViewById(R.id.quantity)
    val decreaseQty: MaterialButton = itemView.findViewById(R.id.decrease_quantity)
    val increaseQty: MaterialButton = itemView.findViewById(R.id.increase_quantity)
    val deleteQty: MaterialButton = itemView.findViewById(R.id.delete)
}