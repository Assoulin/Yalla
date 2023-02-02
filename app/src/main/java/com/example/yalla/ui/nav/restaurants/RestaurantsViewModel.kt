package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.RestaurantForRv
import com.example.yalla.models.RestaurantsByDestination

class RestaurantsViewModel : ViewModel() {

//    fun getLiveRestaurantsForRv(restaurantsByDestination: RestaurantsByDestination): LiveData<List<RestaurantForRv>> {
//
//    }

    fun getLiveRestaurantsByDestinationId(chosenDestinationId: Int): LiveData<RestaurantsByDestination> =
        YallaApplication.repository.getRestaurantsByDestination(chosenDestinationId)


}