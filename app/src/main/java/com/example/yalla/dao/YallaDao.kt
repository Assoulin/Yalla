package com.example.yalla.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.yalla.models.*
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv

@Dao
interface RestaurantDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertDestination(destination: Destination)

    @Insert(onConflict = REPLACE)
    suspend fun insertDestinations(destinations: List<Destination>)

    @Insert
    suspend fun insertAddress(addressModel: Address)

    @Insert(onConflict = REPLACE)
    suspend fun insertAddresses(addresses: List<Address>)

    @Insert(onConflict = REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Insert(onConflict = REPLACE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Insert(onConflict = REPLACE)
    suspend fun insertDestinationsRestaurants(destinationRestaurants: List<DestinationRestaurant>)

    @Insert(onConflict = REPLACE)
    suspend fun insertDailySchedules(dailySchedules: List<DailySchedule>)

    @Insert(onConflict = REPLACE)
    suspend fun insertDailySchedule(dailySchedule: DailySchedule)

    //Entity that does not exist in the API
    @Insert(onConflict = REPLACE)
    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom)

    @Insert(onConflict = REPLACE)
    suspend fun insertLikedRestaurants(likedRestaurants: List<LikedRestaurant>)

    @Insert(onConflict = REPLACE)
    suspend fun insertDishes(dishes: List<Dish>)

    @Insert(onConflict = REPLACE)
    suspend fun insertMenuTitles(menuTitles: List<MenuTitle>)

    @Delete
    suspend fun deleteLikedRestaurants(unlikedRestaurants: List<LikedRestaurant>)

    @Query("SELECT * FROM LikedRestaurant")
    fun getLikedRestaurants(): LiveData<List<LikedRestaurant>>


    @Transaction
    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:chosenDestinationId")
    fun getRestaurantsByDestination(chosenDestinationId: Int): LiveData<RestaurantsByDestination>

    @Transaction
    @Query("SELECT * FROM MenuTitle WHERE restaurantId=:chosenRestaurantId")
    fun getMenuTitleDishes(chosenRestaurantId: Int): LiveData<List<MenuTitleDishes>>

    @Transaction
    @Query(
        "select * from restaurant " +
                "join destinationRestaurant on restaurant.restaurantId=destinationRestaurant.restaurantId " +
                "and destinationRestaurant.destinationId=:chosenDestinationId and isActive = 1 " +
                "join address on restaurant.addressId=address.addressId " +
                "join dailySchedule on dailySchedule.restaurantId = restaurant.restaurantId and dayOfWeek=:currentDay"
    )
    fun getRestaurantsForRv(
        chosenDestinationId: Int,
        currentDay: Int
    ): LiveData<List<RestaurantForRv>>

    @Transaction
    @Query(
        "select * from restaurant " +
                "join destinationRestaurant on restaurant.restaurantId=destinationRestaurant.restaurantId " +
                "and destinationRestaurant.destinationId=:chosenDestinationId and isActive=1 and promoted = 1 " +
                "join address on restaurant.addressId=address.addressId " +
                "join dailySchedule on dailySchedule.restaurantId = restaurant.restaurantId and dayOfWeek=:currentDay"
    )
    fun getHotRestaurantsForRv(
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

    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:chosenDestinationId AND restaurantId=:restaurantId LIMIT 1")
    fun getDeliveryDetails(
        chosenDestinationId: Int,
        restaurantId: Int
    ): LiveData<DestinationRestaurant>


    @Query("SELECT destinationName FROM DESTINATION WHERE destinationId=:chosenRestaurantDestinationId LIMIT 1")
    fun getDestinationNameById(chosenRestaurantDestinationId: Int): LiveData<String>

}