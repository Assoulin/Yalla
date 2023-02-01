package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.RestaurantItemBinding
import com.example.yalla.models.Restaurant
import com.squareup.picasso.Picasso
import retrofit2.http.Url
import java.net.URL

class RestaurantAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RestaurantItemBinding.inflate(inflater, parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]

        with(holder.binding) {
            tvDescription.text = restaurant.description
            tvName.text = restaurant.restaurantName
            //todo: add delivery price+schedule+like button
            Picasso.get().load("https://drive.google.com/file/d/1wP9pY7IFukRgzRsUBpTbSfyg_9O4tLdz/view?usp=share_link").into(this.ivPoster)
        }
    }

    override fun getItemCount(): Int = restaurants.size
}

class RestaurantViewHolder(val binding: RestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root)