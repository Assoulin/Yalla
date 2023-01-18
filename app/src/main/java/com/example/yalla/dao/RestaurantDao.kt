package com.example.yalla.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.yalla.models.*

@Dao
interface RestaurantDao {
    //Should we use LiveData? What is the rule for using it.
    @Query("SELECT * FROM Destination")
    fun getDestinations(): List<Destination>

    //TODO: Ask a teacher about how the joint object looks like
    @Transaction
    @Query("SELECT * FROM Destination WHERE destinationId=:chosenDestinationId LIMIT 1")
    suspend fun getRestaurantsByDestination(chosenDestinationId:Int): RestaurantsByDestination

    @Query("SELECT * FROM DailySchedule WHERE dayOfWeek =:today ORDER BY restaurantId")
    suspend fun getDailyScheduleForToday(today: Int): List<DailySchedule>

    @Transaction
    @Query("SELECT * FROM Restaurant WHERE restaurantId =:chosenRestaurantId")
    suspend fun getDishesByRestaurant(chosenRestaurantId: Int): RestaurantDishes

    @Transaction
    @Query("SELECT * FROM dish WHERE restaurantId =:chosenDishId")
    suspend fun getAdditionsByDish(chosenDishId: Int): AdditionsByDish

    //Search tools:
    @Query("SELECT * FROM restaurant ORDER BY restaurantId")
    suspend fun getRestaurants(): List<Restaurant>

    @Query("SELECT * FROM dish ORDER BY categoryTag")
    suspend fun getDishes():List<Dish>
}