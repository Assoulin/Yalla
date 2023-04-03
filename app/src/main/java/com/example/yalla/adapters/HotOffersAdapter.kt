package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.HotOfferItemBinding
import com.example.yalla.models.Dish
import com.squareup.picasso.Picasso

private const val RIGHT_WORD_COUNT = 12

class HotOffersAdapter(
    private val hotOffers: List<Dish>,
    private val onHotOfferClicked: (Dish) -> Unit
) :
    RecyclerView.Adapter<HotOfferViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotOfferViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HotOfferItemBinding.inflate(inflater, parent, false)
        return HotOfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotOfferViewHolder, position: Int) {
        val hotOffer = hotOffers[position]
        with(holder.binding) {
            tvHotOfferName.text = hotOffer.dishName
            tvPrice.text = hotOffer.priceToString

            var descriptionWordsList =
                hotOffer.description.split(" ")

            var rightSizeDescriptionString = hotOffer.description

            if (descriptionWordsList.size > RIGHT_WORD_COUNT) {
                descriptionWordsList =
                    descriptionWordsList.slice(0 until RIGHT_WORD_COUNT)
                rightSizeDescriptionString = descriptionWordsList.joinToString(" ", postfix = "...")
            }
            tvHotOfferDescription.text = rightSizeDescriptionString
            tvKosher.text = hotOffer.kosherTag.split(", ")[0]
            tvKosherGroup.text = hotOffer.groupTag

            Picasso.get().load(hotOffer.imageUrl).into(hotOfferPoster)

            if (!hotOffer.available) {
                tvOutOfStock.visibility = View.VISIBLE
            }
            root.setOnClickListener {
                onHotOfferClicked.invoke(hotOffer)
            }
        }
    }

    override fun getItemCount() =
        hotOffers.size

}

class HotOfferViewHolder(val binding: HotOfferItemBinding) :
    RecyclerView.ViewHolder(binding.root)