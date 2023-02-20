package com.example.yalla.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yalla.models.*
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestination(destination: Destination)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestinations(destinations: List<Destination>)

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailySchedules(dailySchedules: List<DailySchedule>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailySchedule(dailySchedule: DailySchedule)

    //Entity that does not exist in the API
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedRestaurants(likedRestaurants: List<LikedRestaurant>)

    @Delete
    suspend fun deleteLikedRestaurants(unlikedRestaurants: List<LikedRestaurant>)

    @Query("SELECT * FROM LikedRestaurant")
    fun getLikedRestaurants(): LiveData<List<LikedRestaurant>>


    @Transaction
    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:chosenDestinationId")
    fun getRestaurantsByDestination(chosenDestinationId: Int): LiveData<RestaurantsByDestination>


    @Transaction
    @Query(
        "select * from restaurant " +
                "inner join destinationrestaurant on restaurant.restaurantId=destinationrestaurant.restaurantId " +
                "and destinationrestaurant.destinationId=:chosenDestinationId and isActive=1 " +
                "inner join address on restaurant.addressId=address.addressId " +
                "inner join dailyschedule on dailyschedule.restaurantId = restaurant.restaurantId and dayOfWeek=:currentDay"
    )
    fun getRestaurantsForRv(
        chosenDestinationId: Int,
        currentDay: Int
    ): LiveData<List<RestaurantForRv>>

    @Transaction
    @Query("SELECT * FROM DestinationRestaurant")
    fun getRestaurantsByDestinations(): LiveData<RestaurantsByDestination>

    @Query("SELECT * FROM Destination")
    fun getDestinations(): LiveData<List<Destination>>

    @Query("SELECT * FROM Address WHERE addressId=:currentAddressId")
    fun getAddress(currentAddressId: Int): LiveData<Address>

    @Query("SELECT * FROM DailySchedule WHERE restaurantId=:chosenRestaurantId AND dayOfWeek=:dayOfWeek LIMIT 1")
    fun getTodayScheduleByRestaurant(
        chosenRestaurantId: Int,
        dayOfWeek: Int
    ): LiveData<DailySchedule>

    @Transaction
    @Query("SELECT * FROM Restaurant WHERE addressId=:addressId")
    fun getRestaurantAddress(addressId: Int): LiveData<RestaurantAddress>

    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:chosenDestinationId AND restaurantId=:restaurantId LIMIT 1")
    fun getDeliveryDetails(
        chosenDestinationId: Int,
        restaurantId: Int
    ): LiveData<DestinationRestaurant>

//    @Query("delete * from LikedRestaurant")
//    suspend fun deleteAllLikedRestaurants()

}