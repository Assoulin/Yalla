package com.example.yalla.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.MainPageRestaurantItemBinding
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class LikedRestaurantsAdapter(
    private val likedRestaurants: List<RestaurantForRv>,
    // private val onHotRestClicked: (RestaurantForRv) -> Unit
) :
    RecyclerView.Adapter<LikedRestaurantsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedRestaurantsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainPageRestaurantItemBinding.inflate(inflater, parent, false)
        return LikedRestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikedRestaurantsViewHolder, position: Int) {
        val restaurantForRv = likedRestaurants[position]
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


            llRestaurantItem.setOnClickListener {
                //onHotRestClicked.invoke(restaurantForRv)
            }
        }


    }

    override fun getItemCount() =
        likedRestaurants.size

}

class LikedRestaurantsViewHolder(val binding: MainPageRestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root)
