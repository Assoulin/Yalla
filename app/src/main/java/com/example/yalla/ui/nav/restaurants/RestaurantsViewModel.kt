package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.Destination
import com.example.yalla.models.RestaurantsByDestination

class RestaurantsViewModel : ViewModel() {

    fun getLiveRestaurantsByChosenDestinationId(chosenDestinationId: Int): LiveData<RestaurantsByDestination> =
        YallaApplication.repository.getRestaurantsByDestination(chosenDestinationId)


}