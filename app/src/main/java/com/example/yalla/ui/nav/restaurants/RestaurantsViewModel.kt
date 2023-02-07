package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.DailySchedule
import com.example.yalla.models.DestinationRestaurant
import com.example.yalla.models.RestaurantForRv
import com.example.yalla.models.RestaurantsByDestination

class RestaurantsViewModel : ViewModel() {
//    fun getRestaurantsForRv(chosenDestinationId: Int): LiveData<List<RestaurantForRv>>{
//        val restaurantForRvList = mutableListOf<RestaurantForRv>()
//        getLiveRestaurantsByDestinationId(chosenDestinationId).observe(RestaurantsFragment().)
//    }

    fun getLiveRestaurantsByDestinationId(chosenDestinationId: Int): LiveData<RestaurantsByDestination> =
        YallaApplication.repository.getRestaurantsByDestination(chosenDestinationId)

    fun getRestaurantAddress(addressId: Int) =
        YallaApplication.repository.getRestaurantAddress(addressId)

    fun getTodayScheduleByRestaurant(chosenRestaurantId: Int, dayOfWeek: Int) =
        YallaApplication.repository.getTodayScheduleByRestaurant(chosenRestaurantId, dayOfWeek)

    fun getDeliveryDetails(chosenDestinationId: Int, restaurantId: Int) =
        YallaApplication.repository.getDeliveryDetails(chosenDestinationId, restaurantId)

//    fun getDailyByResId(restaurantId: Int): DailySchedule {
//        TODO("Not yet implemented")
//    }
//
//    fun getDestinationRestaurant(restaurantId: Int, destinationId: Int): DestinationRestaurant {
//        TODO("Not yet implemented")
//    }


}