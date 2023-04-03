package com.example.yalla.repository

import androidx.lifecycle.LiveData
import com.example.yalla.dao.RestaurantDao
import com.example.yalla.models.*
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.services.YallaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YallaRepository(private val restaurantDao: RestaurantDao) {

    fun getDestinations() = restaurantDao.getDestinations()

    fun getRestaurantsForRv(chosenDestinationId: Int, currentDay: Int) =
        restaurantDao.getRestaurantsForRv(chosenDestinationId, currentDay)

    fun getHotRestaurantsForRv(
        chosenDestinationId: Int, currentDay: Int
    ): LiveData<List<RestaurantForRv>> =
        restaurantDao.getHotRestaurantsForRv(chosenDestinationId, currentDay)

    fun getLikedRestaurants() =
        restaurantDao.getLikedRestaurants()

    fun getMenuTitleDishesByRestaurantId(chosenRestaurantId: Int): LiveData<List<MenuTitleDishes>> =
        restaurantDao.getMenuTitleDishes(chosenRestaurantId)

    fun getLikedRestaurantsByDestId(
        chosenDestIdByArg: Int,
        currentDay: Int
    ): LiveData<List<RestaurantForRv>> =
        restaurantDao.getLikedRestaurantsByDestId(chosenDestIdByArg, currentDay)

    fun getHotOffers(chosenDestId: Int, currentDay: Int): LiveData<List<Dish>> =
        restaurantDao.getHotOffers(chosenDestId, currentDay)

    fun getAdditionsForRvByDishId(id: Int): LiveData<List<AdditionForRv>> =
        restaurantDao.getAdditionsForRvByDishId(id)

    fun getRestaurantForRv(
        restaurantId: Int,
        currentDay: Int,
        chosenDestinationId: Int
    ): LiveData<RestaurantForRv> =
        restaurantDao.getRestaurantForRv(restaurantId, chosenDestinationId, currentDay)

    suspend fun chooseDestinationRefreshRoomFromAPI() {
        withContext(Dispatchers.IO) {
            with(YallaService.create()) {
                //get data from API:
                val destinations = allDestinations().destinations
                val restaurants = allRestaurants().restaurants
                val addresses = allAddresses().addresses
                val destinationsRestaurants = allDestinationsRestaurants().destinationsRestaurants
                val dailySchedules = allDailySchedules().dailySchedules
                val dishes = allDishes().dishes
                val menuTitles = allMenuTitles().menuTitles
                val additions = allAdditions().additions
                val dishAdditions = allDishAddition().dishAdditions
                //save data to Room:
                restaurantDao.insertDestinationsRestaurants(destinationsRestaurants)
                restaurantDao.insertRestaurants(restaurants)
                restaurantDao.insertAddresses(addresses)
                restaurantDao.insertDestinations(destinations)
                restaurantDao.insertDailySchedules(dailySchedules)
                restaurantDao.insertDishes(dishes)
                restaurantDao.insertMenuTitles(menuTitles)
                restaurantDao.insertAdditions(additions)
                restaurantDao.insertDishAdditions(dishAdditions)
            }
        }
    }


    suspend fun insertLikedRestaurants(likedRestaurants: List<LikedRestaurant>) {
        restaurantDao.insertLikedRestaurants(likedRestaurants)
    }

    suspend fun insertFullAddress(fullAddressRoom: FullAddressRoom) {
        restaurantDao.insertFullAddress(fullAddressRoom)
    }

    suspend fun insertAddress(address: Address) {
        restaurantDao.insertAddress(address)
    }

    suspend fun deleteUnlikedRestaurants(unlikedRestaurants: List<LikedRestaurant>) {
        restaurantDao.deleteLikedRestaurants(unlikedRestaurants)
    }


}