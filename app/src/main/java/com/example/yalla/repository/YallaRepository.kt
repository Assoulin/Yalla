package com.example.yalla.repository

import com.example.yalla.dao.RestaurantDao
import com.example.yalla.services.YallaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YallaRepository(private val yallaService: YallaService) {

    suspend fun getDestinations() = yallaService.getDestinations()



//    suspend fun refreshRestaurants(){
//        withContext(Dispatchers.IO){
//            //TODO: YallaService
//        }
    //}
}