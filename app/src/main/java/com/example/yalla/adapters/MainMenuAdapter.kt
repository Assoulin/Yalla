package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.MenuTitleItemBinding
import com.example.yalla.models.MenuTitleDishes

private const val COLUMN_NUMBER = 2


class MainMenuAdapter(
    private val menuTitleDishesList: List<MenuTitleDishes>,
    private val onDishClicked: () -> Unit
) :
    RecyclerView.Adapter<MainMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuTitleItemBinding.inflate(inflater, parent, false)
        return MainMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val menuTitleDishes = menuTitleDishesList[position]

        with(holder.binding) {
            tvMenuTitleName.text = menuTitleDishes.menuTitle.titleName
            tvMenuTitleDescription.text = menuTitleDishes.menuTitle.titleDescription

            rvDishes.layoutManager =
                GridLayoutManager(holder.binding.rvDishes.context, COLUMN_NUMBER)
            rvDishes.adapter = DishesAdapter(menuTitleDishes.dishes) {

            }
        }

    }

    override fun getItemCount() =
        menuTitleDishesList.size
}

class MainMenuViewHolder(val binding: MenuTitleItemBinding) :
    RecyclerView.ViewHolder(binding.root)