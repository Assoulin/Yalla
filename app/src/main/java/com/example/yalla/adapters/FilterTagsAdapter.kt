package com.example.yalla.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.CategoryTagItemBinding

const val TEXT_DEFAULT = Color.WHITE
const val TEXT_SELECTED = "#C2185B"

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

            setOnCheckedChangeListener { cuisineToggleBtn, isChecked ->
                if (isChecked) {
                    cuisineToggleBtn.setTextColor(Color.parseColor(TEXT_SELECTED))
                    cuisineToggleBtn.elevation = 8f
                    onTagClicked.invoke(Pair(tagName, true))
                } else {
                    cuisineToggleBtn.elevation = 0f
                    cuisineToggleBtn.setTextColor(TEXT_DEFAULT)
                    cuisineToggleBtn.background = background
                    onTagClicked.invoke(Pair(tagName, false))
                }


            }
        }
    }


    override fun getItemCount(): Int = filterTags.size

}

class FilterTagViewHolder(val binding: CategoryTagItemBinding) :
    RecyclerView.ViewHolder(binding.root)