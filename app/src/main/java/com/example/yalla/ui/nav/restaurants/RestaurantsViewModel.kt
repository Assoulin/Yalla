package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.yalla.YallaApplication
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.utils.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class RestaurantsViewModel : BaseViewModel() {
    val liveLikedRestaurantsOriginal: LiveData<List<LikedRestaurant>> = YallaApplication.repository.getLikedRestaurants()


    private lateinit var _currentChangesInLikedRestaurants: MutableLiveData<MutableList<LikedRestaurant>>
    val currentChangesInLikedRestaurants: LiveData<MutableList<LikedRestaurant>>
        get() = _currentChangesInLikedRestaurants

    fun addLikedRestaurant(lr: LikedRestaurant) {
        _currentChangesInLikedRestaurants.value!!.add(lr)
    }

    fun removeLikedRestaurant(lr: LikedRestaurant) {
        _currentChangesInLikedRestaurants.value!!.remove(lr)
    }
    //Todo: Below is not working!! try delete or check how to write update correctly.
    fun deleteUnlikedRestaurantsFromRoom(list: List<LikedRestaurant>) {
        viewModelScope.launch {
            YallaApplication.repository.deleteUnlikedRestaurants(list)
        }
    }

    fun insertLikedRestaurantsToRoom(list: List<LikedRestaurant>) {
        viewModelScope.launch {
            YallaApplication.repository.insertLikedRestaurants(list)
        }
    }

    fun initCurrentChangesInLikedRestaurants(list: List<LikedRestaurant>) {
        _currentChangesInLikedRestaurants = MutableLiveData(list.toMutableList())
    }

    private val currentDay =
        Calendar.getInstance().get(Calendar.DAY_OF_WEEK)


    fun getRestaurantsForRv(chosenDestinationId: Int): LiveData<List<RestaurantForRv>> =
        YallaApplication.repository.getRestaurantsForRv(chosenDestinationId, currentDay)

    override suspend fun refresh() {
        YallaApplication.repository.refreshRoomFromAPI()
    }


}