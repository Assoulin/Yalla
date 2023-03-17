package com.example.yalla.ui.nav.restaurants.restaurant_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.Dish
import com.example.yalla.models.MenuTitle
import com.example.yalla.models.MenuTitleDishes

class RestaurantMenuViewModel : ViewModel() {
    fun getMenuTitleDishesByRestaurantId(chosenRestaurantId: Int): LiveData<List<MenuTitleDishes>> =
        YallaApplication.repository.getMenuTitleDishesByRestaurantId(chosenRestaurantId)

    fun getDestinationNameById(chosenRestaurantDestinationId: Int): LiveData<String> =
        YallaApplication.repository.getDestinationNameById(chosenRestaurantDestinationId)


}