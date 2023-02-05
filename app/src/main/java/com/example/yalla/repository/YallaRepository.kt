package com.example.yalla.repository

import com.example.yalla.dao.RestaurantDao
import com.example.yalla.models.Address
import com.example.yalla.models.FullAddressRoom
import com.example.yalla.services.YallaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YallaRepository(private val restaurantDao: RestaurantDao) {

    fun getDestinations() = restaurantDao.getDestinations()

    fun getRestaurantsByDestination(chosenDestinationId: Int) =
        restaurantDao.getRestaurantsByDestination(chosenDestinationId)

    fun getAddress(currentAddressId: Int) = restaurantDao.getAddress(currentAddressId)

    fun getTodayScheduleByRestaurant(chosenRestaurantId: Int, dayOfWeek: Int) =
        restaurantDao.getTodayScheduleByRestaurant(chosenRestaurantId, dayOfWeek)

    fun getRestaurantAddress(addressId: Int) = restaurantDao.getRestaurantAddress(addressId)

    fun getDeliveryDetails(chosenDestinationId: Int, restaurantId: Int) =
        restaurantDao.getDeliveryDetails(chosenDestinationId, restaurantId)


    //take new data from the api and saves it to the Room DB
//    suspend fun refreshDestinations() {
//        withContext(Dispatchers.IO) {
//            with(YallaService.create()) {
//
//                //get data from API:
//
//
//                //save data to Room:
//
//            }
//        }
//    }

    suspend fun refreshRoomFromAPI() {
        withContext(Dispatchers.IO) {
            with(YallaService.create()) {
                //get data from API:
                val destinations = allDestinations().destinations
                val restaurants = allRestaurants().restaurants
                val addresses = allAddresses().addresses
                val destinationsRestaurants = allDestinationsRestaurants().destinationsRestaurants
                val dailySchedules = allDailySchedules().dailySchedules

                //save data to Room:
                restaurantDao.insertDestinationsRestaurants(destinationsRestaurants)
                restaurantDao.insertRestaurants(restaurants)
                restaurantDao.insertAddresses(addresses)
                restaurantDao.insertDestinations(destinations)
                restaurantDao.insertDailySchedules(dailySchedules)
            }
        }
    }

    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom) {
        restaurantDao.insertFullAddress(fullAddressRoom)
    }

    suspend fun insertAddress(address: Address) {
        restaurantDao.insertAddress(address)
    }
}