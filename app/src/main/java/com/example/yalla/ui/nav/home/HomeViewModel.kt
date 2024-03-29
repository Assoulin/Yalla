package com.example.yalla.ui.nav.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.Dish
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import java.util.*

class HomeViewModel : ViewModel() {
    fun hotRestaurants(chosenDestinationId: Int): LiveData<List<RestaurantForRv>> =
        YallaApplication.repository.getHotRestaurantsForRv(chosenDestinationId, currentDay)

    fun likedRestaurants(chosenDestIdByArg: Int): LiveData<List<RestaurantForRv>> =
        YallaApplication.repository.getLikedRestaurantsByDestId(chosenDestIdByArg, currentDay)

    fun hotOffers(chosenDestId: Int): LiveData<List<Dish>> =
        YallaApplication.repository.getHotOffers(chosenDestId, currentDay)

    private var currentDay = 0

    init {
        currentDay =
            Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    }
}