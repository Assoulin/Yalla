package com.example.yalla.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.yalla.models.*
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv

const val LIKED_RESTAURANT_FOR_RV_QUERY =
    "SELECT res.restaurantId, res.cuisine, res.description, res.imageUrl, res.restaurantName, address.apartment, address.entrance, address.houseNumber, address.street, ds.openingHour, ds.closingHour, dr.deliveryPrice, dr.deliveryTime, d.destinationName " +
            " FROM restaurant AS res " +
            " JOIN destinationRestaurant AS dr ON res.restaurantId=dr.restaurantId " +
            " AND dr.destinationId=:chosenDestIdByArg AND isActive = 1 " +
            " JOIN destination AS d ON d.destinationId =:chosenDestIdByArg " +
            " JOIN address ON res.addressId=address.addressId " +
            " JOIN dailySchedule AS ds ON ds.restaurantId = res.restaurantId AND dayOfWeek=:currentDay" +
            " JOIN LikedRestaurant AS lr ON lr.restaurantId = res.restaurantId"

const val HOT_RESTAURANT_FOR_RV_QUERY =
    "SELECT res.restaurantId, res.cuisine, res.description, res.imageUrl, res.restaurantName, address.apartment, address.entrance, address.houseNumber, address.street, ds.openingHour, ds.closingHour, dr.deliveryPrice, dr.deliveryTime, d.destinationName " +
            " FROM restaurant AS res " +
            " JOIN destinationRestaurant AS dr ON res.restaurantId=dr.restaurantId " +
            " AND dr.destinationId=:chosenDestinationId AND isActive = 1 and promoted = 1 " +
            " JOIN destination AS d ON d.destinationId =:chosenDestinationId " +
            " JOIN address ON res.addressId=address.addressId " +
            " JOIN dailySchedule AS ds ON ds.restaurantId = res.restaurantId AND dayOfWeek=:currentDay"

const val RESTAURANTS_FOR_RV_QUERY =
    "SELECT res.restaurantId, res.cuisine, res.description, res.imageUrl, res.restaurantName, address.apartment, address.entrance, address.houseNumber, address.street, ds.openingHour, ds.closingHour, dr.deliveryPrice, dr.deliveryTime, d.destinationName " +
            " FROM restaurant AS res " +
            " JOIN destinationRestaurant AS dr ON res.restaurantId=dr.restaurantId " +
            " AND dr.destinationId=:chosenDestinationId AND isActive = 1 " +
            " JOIN address ON res.addressId=address.addressId " +
            " JOIN destination AS d ON d.destinationId =:chosenDestinationId " +
            " JOIN dailySchedule AS ds ON ds.restaurantId = res.restaurantId AND dayOfWeek=:currentDay"
const val RESTAURANT_FOR_RV_QUERY =
    "SELECT res.restaurantId, res.cuisine, res.description, res.imageUrl, res.restaurantName, address.apartment, address.entrance, address.houseNumber, address.street, ds.openingHour, ds.closingHour, dr.deliveryPrice, dr.deliveryTime, d.destinationName " +
            " FROM restaurant AS res " +
            " JOIN destinationRestaurant AS dr ON res.restaurantId=dr.restaurantId " +
            " AND dr.destinationId=:chosenDestinationId AND isActive = 1 " +
            " JOIN address ON res.addressId=address.addressId " +
            " JOIN destination AS d ON d.destinationId =:chosenDestinationId " +
            " JOIN dailySchedule AS ds ON ds.restaurantId = res.restaurantId AND dayOfWeek=:currentDay " +
            " WHERE res.restaurantId=:id LIMIT 1"

const val HOT_OFFERS_RESTAURANT_FOR_RV_QUERY =
    "SELECT D.dishName,D.dishId, D.imageUrl, D.restaurantId, D.description, D.available, D.categoryTag, D.groupTag, D.kosherTag, D.promoted, D.requireQuantity, D.price, D.menuTitleId,DS.closingHour,DS.openingHour " +
            " FROM Dish AS D " +
            " JOIN Restaurant AS RES ON RES.restaurantId =  D.restaurantId " +
            " JOIN DestinationRestaurant AS DR ON DR.restaurantId = RES.restaurantId " +
            " AND DR.destinationId =:chosenDestId " +
            " JOIN dailySchedule AS DS ON DS.restaurantId = RES.restaurantId AND dayOfWeek=:currentDay " +
            " where D.promoted = 1 AND D.available = 1"

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

    @Insert(onConflict = REPLACE)
    fun insertAdditions(additions: List<Addition>)

    @Insert(onConflict = REPLACE)
    fun insertDishAdditions(dishAdditions: List<DishAddition>)

    @Delete
    suspend fun deleteLikedRestaurants(unlikedRestaurants: List<LikedRestaurant>)

    @Query("SELECT * FROM LikedRestaurant")
    fun getLikedRestaurants(): LiveData<List<LikedRestaurant>>

    @Transaction
    @Query(LIKED_RESTAURANT_FOR_RV_QUERY)
    fun getLikedRestaurantsByDestId(
        chosenDestIdByArg: Int,
        currentDay: Int
    ): LiveData<List<RestaurantForRv>>

    @Transaction
    @Query(HOT_OFFERS_RESTAURANT_FOR_RV_QUERY)
    fun getHotOffers(chosenDestId: Int, currentDay: Int): LiveData<List<Dish>>


    @Transaction
    @Query("SELECT * FROM DestinationRestaurant WHERE destinationId=:chosenDestinationId")
    fun getRestaurantsByDestination(chosenDestinationId: Int): LiveData<RestaurantsByDestination>

    @Transaction
    @Query("SELECT * FROM MenuTitle WHERE restaurantId=:chosenRestaurantId")
    fun getMenuTitleDishes(chosenRestaurantId: Int): LiveData<List<MenuTitleDishes>>

    @Transaction
    @Query(RESTAURANTS_FOR_RV_QUERY)
    fun getRestaurantsForRv(
        chosenDestinationId: Int,
        currentDay: Int
    ): LiveData<List<RestaurantForRv>>

    @Transaction
    @Query(RESTAURANT_FOR_RV_QUERY)
    fun getRestaurantForRv(
        id: Int,
        chosenDestinationId: Int,
        currentDay: Int
    ): LiveData<RestaurantForRv>

    @Transaction
    @Query(HOT_RESTAURANT_FOR_RV_QUERY)
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

    @Transaction
    @Query(
        "SELECT *" +
                " FROM DishAddition AS DA " +
                " JOIN Addition AS A ON A.additionId = DA.additionId " +
                " WHERE DA.dishId=:id "
    )
    fun getAdditionsForRvByDishId(id: Int): LiveData<List<AdditionForRv>>


}