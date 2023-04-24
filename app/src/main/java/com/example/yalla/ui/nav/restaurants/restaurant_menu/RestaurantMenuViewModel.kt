package com.example.yalla.ui.nav.restaurants.restaurant_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.MenuTitleWithDishes
import com.example.yalla.models.x_retrofit_models.RestaurantForRv

class RestaurantMenuViewModel : ViewModel() {

    fun getMenuTitleDishesByRestaurantId(chosenRestaurantId: Int): LiveData<List<MenuTitleWithDishes>> =
        YallaApplication.repository.getMenuTitleDishesByRestaurantId(chosenRestaurantId)

    private lateinit var _chosenRest: RestaurantForRv
    val chosenRest: RestaurantForRv
        get() = _chosenRest



    fun setChosenRest(rest: RestaurantForRv?) {
        rest?.let {
            _chosenRest = it
        }
    }


}