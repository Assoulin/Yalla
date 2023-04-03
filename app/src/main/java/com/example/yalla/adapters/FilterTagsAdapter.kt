package com.example.yalla.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.CategoryTagItemBinding


class FilterTagsAdapter(
    private val filterTags: List<String>,
    private val onTagClicked: (Pair<String, Boolean>) -> Unit
) :
    RecyclerView.Adapter<FilterTagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterTagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryTagItemBinding.inflate(inflater, parent, false)
        return FilterTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterTagViewHolder, position: Int) {
        val tagName = filterTags[position]
        with(holder.binding.cuisineToggleBtn) {
            text = tagName
            textOn = tagName
            textOff = tagName

            val textSelectedColor = ResourcesCompat.getColor(this.resources, R.color.pink, null)

            setOnCheckedChangeListener { cuisineToggleBtn, isChecked ->
                if (isChecked) {
                    cuisineToggleBtn.setTextColor(textSelectedColor)
                    onTagClicked.invoke(Pair(tagName, true))
                } else {
                    cuisineToggleBtn.setTextColor(Color.WHITE)
                    onTagClicked.invoke(Pair(tagName, false))
                }
            }
        }
    }


    override fun getItemCount(): Int = filterTags.size

}

class FilterTagViewHolder(val binding: CategoryTagItemBinding) :
    RecyclerView.ViewHolder(binding.root)