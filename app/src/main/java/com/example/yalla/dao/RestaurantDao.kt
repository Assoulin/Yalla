package com.example.yalla.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yalla.models.*

@Dao
interface RestaurantDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addRestaurants(restaurants: List<Restaurant>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addDishes(dishes: List<Dish>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestination(destinations: Destination)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestinations(destinations: List<Destination>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addDailySchedules(dailySchedules: List<DailySchedule>)


    //Should we use LiveData? What is the rule for using it.
    @Query("SELECT * FROM Destination")
    fun getDestinations(): LiveData<List<Destination>>

//    //TODO: Ask a teacher about how the joint object looks like
//    @Transaction
//    @Query("SELECT * FROM Destination WHERE destinationId=:chosenDestinationId LIMIT 1")
//    suspend fun getRestaurantsByDestination(chosenDestinationId: Int): RestaurantsByDestination
//
//    @Query("SELECT * FROM DailySchedule WHERE dayOfWeek =:today ORDER BY restaurantId")
//    suspend fun getDailyScheduleForToday(today: Int): List<DailySchedule>
//
//    @Transaction
//    @Query("SELECT * FROM Restaurant WHERE restaurantId =:chosenRestaurantId")
//    suspend fun getDishesByRestaurant(chosenRestaurantId: Int): RestaurantDishes
//
//    @Transaction
//    @Query("SELECT * FROM dish WHERE restaurantId =:chosenDishId")
//    suspend fun getAdditionsByDish(chosenDishId: Int): AdditionsByDish


}