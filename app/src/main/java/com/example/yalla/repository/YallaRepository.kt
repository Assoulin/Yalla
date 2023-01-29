package com.example.yalla.repository

import com.example.yalla.dao.RestaurantDao
import com.example.yalla.models.FullAddressRoom
import com.example.yalla.services.YallaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YallaRepository(private val restaurantDao: RestaurantDao) {

    fun getDestinations() = restaurantDao.getDestinations()

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

    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom) {
        restaurantDao.insertFullAddress(fullAddressRoom)
    }
}


