package com.example.yalla.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.CategoryTagItemBinding

const val SELECTED = "#F08323"
const val TEXT_SELECTED = Color.WHITE
const val DEFAULT = "#E4E4E4"
const val TEXT_DEFAULT = "#C2185B"

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
                    cuisineToggleBtn.setBackgroundColor(Color.parseColor(SELECTED))
                    cuisineToggleBtn.setTextColor(TEXT_SELECTED)
                    onTagClicked.invoke(Pair(tagName, true))
                } else {
                    cuisineToggleBtn.setBackgroundColor(Color.parseColor(DEFAULT))
                    cuisineToggleBtn.setTextColor(Color.parseColor(TEXT_DEFAULT))
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