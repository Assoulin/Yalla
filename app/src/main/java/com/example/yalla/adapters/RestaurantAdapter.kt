package com.example.yalla.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.RestaurantItemBinding

import com.example.yalla.models.RestaurantForRv
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent


class RestaurantAdapter(
    private val restaurants: List<RestaurantForRv>,
//    private val onResClicked: (RestaurantForRv) -> Unit,
    private val onLikeClicked: (Boolean) -> Unit,

) :
    RecyclerView.Adapter<RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RestaurantItemBinding.inflate(inflater, parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantForRv = restaurants[position]
        with(holder.binding) {
            tvDescription.text = restaurantForRv.description
            tvName.text = restaurantForRv.restaurantName
            Picasso.get().load(restaurantForRv.imageUrl).into(ivPoster)
            tvDeliveryPrice.text = restaurantForRv.getDeliveryPrice
            tvOpeningHours.text = restaurantForRv.getOpenStatusMessage()

            btnLike.setOnClickListener {
                onLikeClicked.invoke(restaurantForRv.isLiked)

            }
            llRestaurant.setOnClickListener {
//                onResClicked.invoke(restaurantForRv)

            }
        }
    }

    override fun getItemCount(): Int = restaurants.size
}

class RestaurantViewHolder(val binding: RestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root)