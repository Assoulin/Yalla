package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import com.example.yalla.YallaApplication
import com.example.yalla.models.*
import com.example.yalla.utils.BaseViewModel
import java.util.*

class RestaurantsViewModel : BaseViewModel() {
    private val currentDay =
        Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    fun getLiveRestaurantsByDestinationId(chosenDestinationId: Int): LiveData<RestaurantsByDestination> =
        YallaApplication.repository.getRestaurantsByDestination(chosenDestinationId)

    fun getRestaurantsForRv(chosenDestinationId: Int): LiveData<List<RestaurantForRv>> =
        YallaApplication.repository.getRestaurantsForRv(chosenDestinationId, currentDay)


    fun getRestaurantAddress(addressId: Int) =
        YallaApplication.repository.getRestaurantAddress(addressId)

    fun getTodayScheduleByRestaurant(chosenRestaurantId: Int, dayOfWeek: Int) =
        YallaApplication.repository.getTodayScheduleByRestaurant(chosenRestaurantId, dayOfWeek)

    fun getDeliveryDetails(chosenDestinationId: Int, restaurantId: Int) =
        YallaApplication.repository.getDeliveryDetails(chosenDestinationId, restaurantId)

    override suspend fun refresh() {
        YallaApplication.repository.refreshRoomFromAPI()
    }

//    fun getDailyByResId(restaurantId: Int): DailySchedule {
//        TODO("Not yet implemented")
//    }
//
//    fun getDestinationRestaurant(restaurantId: Int, destinationId: Int): DestinationRestaurant {
//        TODO("Not yet implemented")
//    }


}