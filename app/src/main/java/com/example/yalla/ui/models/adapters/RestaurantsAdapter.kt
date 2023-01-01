package com.example.yalla.ui.models.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.lec12.utils.inflater
import com.example.yalla.databinding.RestaurantItemBinding
import com.example.yalla.ui.models.Restaurant

class RestaurantsAdapter(val restaurants: List<Restaurant>) :
    Adapter<RestaurantsAdapter.RestaurantViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(RestaurantItemBinding.inflate(parent.inflater, parent, false))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        with(holder.binding){
            tvName.text = restaurant.restaurantName
            tvDescription.text = restaurant.description
        }
        TODO("Not yet implemented")
    }

    override fun getItemCount() = restaurants.size

    class RestaurantViewHolder(val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}