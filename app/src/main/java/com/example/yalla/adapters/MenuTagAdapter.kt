package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.MenuTagItemBinding
import com.example.yalla.models.MenuTitle

class MenuTagAdapter(
    private val menus: List<MenuTitle>,
    private val onTagClicked: (MenuTitle) -> Unit
) :
    RecyclerView.Adapter<MenuTagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuTagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuTagItemBinding.inflate(inflater, parent, false)
        return MenuTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuTagViewHolder, position: Int) {

        val menuTitle = menus[position]
        with(holder.binding.tagToggleBtn) {
            text = menuTitle.titleName
            textOn = menuTitle.titleName
            textOff = menuTitle.titleName

            val textSelectedColor = ResourcesCompat.getColor(this.resources, R.color.orange, null)
            val normalColor = ResourcesCompat.getColor(this.resources, R.color.black, null)

            setOnCheckedChangeListener { cuisineToggleBtn, isChecked ->
                if (isChecked) {
                    cuisineToggleBtn.setTextColor(textSelectedColor)
                    onTagClicked.invoke(menuTitle)
                } else {
                    cuisineToggleBtn.setTextColor(normalColor)
                    onTagClicked.invoke(menuTitle)
                }
            }
        }
    }

    override fun getItemCount(): Int =
        menus.size
}

class MenuTagViewHolder(val binding: MenuTagItemBinding) :
    RecyclerView.ViewHolder(binding.root)