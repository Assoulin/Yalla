package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.DishItemBinding
import com.example.yalla.databinding.MenuTitleItemBinding
import com.example.yalla.databinding.RestaurantItemBinding
import com.example.yalla.models.Dish
import com.squareup.picasso.Picasso

private const val RIGHT_WORD_COUNT = 12

class DishesAdapter(
    private val dishList: List<Dish>,
    private val onDishClicked: () -> Unit
) :
    RecyclerView.Adapter<DishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DishItemBinding.inflate(inflater, parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishList[position]
        with(holder.binding) {
            tvDishName.text = dish.dishName
            tvPrice.text = dish.priceToString

            var descriptionWordsList =
                dish.description.split(" ")

            var rightSizeDescriptionString = dish.description

            if (descriptionWordsList.size > RIGHT_WORD_COUNT) {
                descriptionWordsList =
                    descriptionWordsList.slice(0 until RIGHT_WORD_COUNT)
                rightSizeDescriptionString = descriptionWordsList.joinToString(" ", postfix = "...")
            }
            tvDishDescription.text = rightSizeDescriptionString
            tvDishKosher.text = dish.kosherTag
            tvKosherGroup.text = dish.groupTag

            Picasso.get().load(dish.imageUrl).into(dishPoster)

            if (!dish.available) {
                tvOutOfStock.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() =
        dishList.size

}

class DishViewHolder(val binding: DishItemBinding) :
    RecyclerView.ViewHolder(binding.root)
