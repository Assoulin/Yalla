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
    suspend fun insertDestination(destination: Destination)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestinations(destinations: List<Destination>)

//    @Query("insert into address (destinationId,street,houseNumber,entrance,apartment,locationInstructions)" +
//            " VALUES(:addressModel.destinationId,:addressModel.street,:addressModel.houseNumber,:addressModel.entrance,:addressModel.apartment,:addressModel.locationInstructions) "
//    + "if not exist (select * from address WHERE street =:addressModel.street AND houseNumber =:addressModel.houseNumber)")
//    @Transaction
    @Insert
    suspend fun insertAddress(addressModel: Address)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddresses(addresses: List<Address>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestinationsRestaurants(destinationRestaurants: List<DestinationRestaurant>)

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addDailySchedules(dailySchedules: List<DailySchedule>)

    //Entity that does not exist in the API
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom)

    @Transaction
    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:chosenDestinationId")
    fun getRestaurantsByDestination(chosenDestinationId:Int): LiveData<RestaurantsByDestination>


    @Query("SELECT * FROM Destination")
    fun getDestinations(): LiveData<List<Destination>>

//    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:")
//    fun getRestaurants(): LiveData<List<Restaurant>>

    @Query("SELECT * FROM Address WHERE addressId=:currentAddressId")
    fun getAddress(currentAddressId:Int): LiveData<Address>



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