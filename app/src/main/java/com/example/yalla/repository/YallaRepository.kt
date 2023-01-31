package com.example.yalla.repository
import com.example.yalla.dao.RestaurantDao
import com.example.yalla.models.Address
import com.example.yalla.models.FullAddressRoom
import com.example.yalla.services.YallaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YallaRepository(private val restaurantDao: RestaurantDao) {

    fun getDestinations() = restaurantDao.getDestinations()

    fun getAddress(currentAddressId: Int) = restaurantDao.getAddress(currentAddressId)

    //take new data from the api and saves it to the Room DB
    suspend fun refreshDestinations() {
        withContext(Dispatchers.IO) {
            with(YallaService.create()) {
                //get data from API:
                val destinations = allDestinations().destinations
                //save data to Room:
                restaurantDao.insertDestinations(destinations)
            }
        }
    }

    suspend fun refreshRestaurants() {
        withContext(Dispatchers.IO) {
            with(YallaService.create()) {
                //get data from API:

                val restaurants = allRestaurants().restaurants
                val addresses = allAddresses().addresses
                //save data to Room:

                restaurantDao.insertRestaurants(restaurants)
                restaurantDao.insertAddresses(addresses)
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