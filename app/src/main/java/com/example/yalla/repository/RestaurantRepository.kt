package com.example.yalla.repository

import com.example.yalla.dao.RestaurantDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    fun getDestinations() = restaurantDao.getDestinations()

    suspend fun refreshRestaurants(){
        withContext(Dispatchers.IO){
            //TODO: YallaService
        }
    }
}