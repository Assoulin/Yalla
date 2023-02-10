package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.CategoryTagItemBinding

class FilterTagsAdapter(
    private val filterTags: List<String>,
    private val onTagClicked: (String) -> Unit
) :
    RecyclerView.Adapter<FilterTagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterTagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryTagItemBinding.inflate(inflater, parent, false)
        return FilterTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterTagViewHolder, position: Int) {
        val tagName = filterTags[position]
        holder.binding.tagChip.text = tagName
        holder.binding.tagChip.setOnClickListener {
            onTagClicked.invoke(tagName)
        }
    }


    override fun getItemCount(): Int = filterTags.size

}

class FilterTagViewHolder(val binding: CategoryTagItemBinding) :
    RecyclerView.ViewHolder(binding.root)