package com.example.yalla.repository

import com.example.yalla.dao.RestaurantDao
import com.example.yalla.models.Address
import com.example.yalla.models.FullAddressRoom
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.services.YallaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YallaRepository(private val restaurantDao: RestaurantDao) {

    fun getDestinations() = restaurantDao.getDestinations()

    fun getRestaurantsForRv(chosenDestinationId: Int, currentDay: Int) =
        restaurantDao.getRestaurantsForRv(chosenDestinationId, currentDay)

    fun getLikedRestaurants() =
        restaurantDao.getLikedRestaurants()

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

    suspend fun insertLikedRestaurants(likedRestaurants: List<LikedRestaurant>) {
        restaurantDao.insertLikedRestaurants(likedRestaurants)
    }

    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom) {
        restaurantDao.insertFullAddress(fullAddressRoom)
    }

    suspend fun insertAddress(address: Address) {
        restaurantDao.insertAddress(address)
    }

    suspend fun updateLikedRestaurants(likedRestaurants: List<LikedRestaurant>) {
        restaurantDao.updateLikedRestaurants(likedRestaurants)
    }
}