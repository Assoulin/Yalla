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

class HotRestaurantsAdapter(
    private val hotRestaurants: List<RestaurantForRv>,
     private val onHotRestClicked: (RestaurantForRv) -> Unit
) :
    RecyclerView.Adapter<HotRestaurantsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotRestaurantsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainPageRestaurantItemBinding.inflate(inflater, parent, false)
        return HotRestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotRestaurantsViewHolder, position: Int) {
        val restaurantForRv = hotRestaurants[position]
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


            root.setOnClickListener {
                onHotRestClicked.invoke(restaurantForRv)
            }
        }


    }

    override fun getItemCount() =
        hotRestaurants.size

}

class HotRestaurantsViewHolder(val binding: MainPageRestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root)
