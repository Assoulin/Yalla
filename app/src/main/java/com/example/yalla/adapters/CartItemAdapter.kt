package com.example.yalla.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.CartItemBinding
import com.example.yalla.models.order.CartItem
import com.squareup.picasso.Picasso


class CartItemAdapter(
    private val cartItems: List<CartItem>,
    private val onRemoveItemClicked: (CartItem) -> Unit,

    ) : RecyclerView.Adapter<CartItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(inflater, parent, false)
        return CartItemViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        with(holder.binding) {
            Picasso.get()
                .load(cartItem.chosenDish.imageUrl)
                .placeholder(
                    ResourcesCompat.getDrawable(
                        root.resources, R.drawable.proggress, null
                    )!!
                )
                .into(ivDishImage)
            tvDishName.text = cartItem.chosenDish.dishName
            var description = cartItem.chosenDish.description
            if (description.length > 25) {
                description = description.substring(0..25) + "..."
                tvDishDescription.text = description
            } else {
                tvDishDescription.text = description
            }
            tvCartItemPrice.text = cartItem.totalItemPrice.toString()
            tvDishQuantity.text = cartItem.quantity.toString()

            ivRemoveItem.setOnClickListener {
                onRemoveItemClicked.invoke(cartItem)
            }
        }


    }

    override fun getItemCount(): Int = cartItems.size
}

class CartItemViewHolder(val binding: CartItemBinding) :
    RecyclerView.ViewHolder(binding.root)