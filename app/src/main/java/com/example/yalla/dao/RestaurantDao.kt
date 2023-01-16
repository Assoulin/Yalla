package com.example.yalla.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.yalla.models.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM Destination")
    suspend fun getDestinations(): List<Destination>

    //TODO: Ask a teacher about how the joint object looks like
    @Transaction
    @Query("SELECT * FROM DestinationsRestaurants WHERE destinationId=:chosenDestinationId  ORDER BY restaurantId")
    suspend fun getRestaurantsByDestination(chosenDestinationId:Int): RestaurantsByDestination

    @Query("SELECT * FROM DailySchedule WHERE dayOfWeek =:today ORDER BY restaurantId")
    suspend fun getDailyScheduleForToday(today: Int): List<DailySchedule>

    @Query("SELECT * FROM Dish WHERE restaurantId =:chosenRestaurantId")
    suspend fun getDishesByRestaurant(chosenRestaurantId: Int): RestaurantDishes

    @Query("SELECT * FROM dish WHERE restaurantId =:chosenDishId")
    suspend fun getAdditionsByDish(chosenDishId: Int): AdditionsByDish


    //Search tools:
    @Query("SELECT * FROM restaurant ORDER BY restaurantId")
    suspend fun getRestaurants(): List<Restaurant>

    @Query("SELECT * FROM dish ORDER BY categoryTag")
    suspend fun getDishes(): List<Dish>
}