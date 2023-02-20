package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.RestaurantItemBinding
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.squareup.picasso.Picasso


class RestaurantAdapter(
    private val restaurants: List<RestaurantForRv>,
    private val likedRestaurants: List<LikedRestaurant>,
    private val onResClicked: (RestaurantForRv) -> Unit,
    private val onLikeClicked: (Int, Boolean) -> Unit,

    ) : RecyclerView.Adapter<RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RestaurantItemBinding.inflate(inflater, parent, false)
        return RestaurantViewHolder(binding)
    }

    //@SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantForRv = restaurants[position]

        with(holder.binding) {
            tvDescription.text = restaurantForRv.description
            tvName.text = restaurantForRv.restaurantName
            Picasso.get().load(restaurantForRv.imageUrl).into(ivPoster)
            tvDeliveryPrice.text = restaurantForRv.getDeliveryPrice
            tvOpeningHours.text = restaurantForRv.getOpenStatusMessage()
            //Like button handler:
            val unlikedDrawable = ResourcesCompat.getDrawable(
                btnLike.resources, R.drawable.ic_unliked, null
            )
            val likedDrawable = ResourcesCompat.getDrawable(
                btnLike.resources, R.drawable.ic_liked, null
            )

            if (likedRestaurants.isNotEmpty()) {
                val isLiked =
                    likedRestaurants.map { likedRestaurant -> likedRestaurant.restaurantId }
                        .contains(restaurantForRv.restaurantId)
                if (isLiked) {
                    btnLike.foreground = likedDrawable
                }
            }
            btnLike.setOnClickListener { btnLike ->
                var isLikedStatus = true
                val currentForeground = btnLike.foreground
                if (currentForeground == unlikedDrawable) {
                    btnLike.foreground = likedDrawable
                } else {
                    btnLike.foreground = unlikedDrawable
                    isLikedStatus = false
                }
                onLikeClicked.invoke(restaurantForRv.restaurantId, isLikedStatus)
            }
            llRestaurant.setOnClickListener {
                onResClicked.invoke(restaurantForRv)
            }
        }


    }

    override fun getItemCount(): Int = restaurants.size
}

class RestaurantViewHolder(val binding: RestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root)