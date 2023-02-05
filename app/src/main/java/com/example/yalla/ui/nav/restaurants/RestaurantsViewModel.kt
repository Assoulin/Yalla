package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.DailySchedule
import com.example.yalla.models.DestinationRestaurant
import com.example.yalla.models.RestaurantForRv
import com.example.yalla.models.RestaurantsByDestination

class RestaurantsViewModel : ViewModel() {


    //    fun getLiveRestaurantsForRv(restaurantsByDestination: RestaurantsByDestination): LiveData<List<RestaurantForRv>> {
//
//    }
    // SELECT (ALL OF RESTAURANT), CLOSING OPENING,  FROM
    fun getLiveRestaurantsByDestinationId(chosenDestinationId: Int): LiveData<RestaurantsByDestination> =
        YallaApplication.repository.getRestaurantsByDestination(chosenDestinationId)

//    fun getDailyByResId(restaurantId: Int): DailySchedule {
//        TODO("Not yet implemented")
//    }
//
//    fun getDestinationRestaurant(restaurantId: Int, destinationId: Int): DestinationRestaurant {
//        TODO("Not yet implemented")
//    }


}