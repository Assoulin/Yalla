package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.yalla.YallaApplication
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class RestaurantsViewModel : BaseViewModel() {


    private lateinit var _liveLikedRestaurantsOriginal: LiveData<List<LikedRestaurant>>
    val liveLikedRestaurantsOriginal
        get() = _liveLikedRestaurantsOriginal

    private lateinit var _currentChangesInLikedRestaurants: MutableLiveData<MutableList<LikedRestaurant>>
    val currentChangesInLikedRestaurants
        get() = _currentChangesInLikedRestaurants

    private var currentDay = 0

        init {
        currentDay =
            Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    }

    fun addLikedRestaurant(lr: LikedRestaurant) {
        _currentChangesInLikedRestaurants.value!!.add(lr)
    }

    fun removeLikedRestaurant(lr: LikedRestaurant) {
        _currentChangesInLikedRestaurants.value!!.remove(lr)
    }

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

    fun getRestaurantsForRv(chosenDestinationId: Int): LiveData<List<RestaurantForRv>> =
        YallaApplication.repository.getRestaurantsForRv(chosenDestinationId, currentDay)

    override suspend fun refresh() {
        _liveLikedRestaurantsOriginal = YallaApplication.repository.getLikedRestaurants()
    }


}