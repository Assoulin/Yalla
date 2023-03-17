package com.example.yalla.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.RestaurantItemBinding
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception


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

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantForRv = restaurants[position]

        with(holder.binding) {
            tvDescription.text = restaurantForRv.description
            tvName.text = restaurantForRv.restaurantName


            val target = object : Target {

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    // bitmap
                    ivPoster.setImageBitmap(bitmap)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    ivPoster.setImageDrawable(errorDrawable)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    ivPoster.setImageDrawable(placeHolderDrawable)
                }
            }

            Picasso.get()
                .load(restaurantForRv.imageUrl)
                .placeholder(
                    ResourcesCompat.getDrawable(
                        root.resources, R.drawable.proggress, null
                    )!!
                )
                .error(
                    ResourcesCompat.getDrawable(
                        root.resources, R.drawable.bag, null
                    )!!
                )
                .into(target)

            tvDeliveryPrice.text = restaurantForRv.getDeliveryPrice
            val result = restaurantForRv.getOpenStatusMessage()

            //tvOpeningHours.text = restaurantForRv.getOpenStatusMessage()
            tvDeliveryTime.text =
                root.context.getString(R.string.delivery_time_is, restaurantForRv.deliveryTime)
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
                var isLikedStatus = false
                val currentForeground = btnLike.foreground
                if (currentForeground == likedDrawable) {
                    btnLike.foreground = unlikedDrawable
                } else {
                    btnLike.foreground = likedDrawable
                    isLikedStatus = true
                }
                onLikeClicked.invoke(restaurantForRv.restaurantId, isLikedStatus)
            }

            llRestaurantItem.setOnClickListener {
                onResClicked.invoke(restaurantForRv)
            }
        }


    }

    override fun getItemCount(): Int = restaurants.size
}

class RestaurantViewHolder(val binding: RestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root)