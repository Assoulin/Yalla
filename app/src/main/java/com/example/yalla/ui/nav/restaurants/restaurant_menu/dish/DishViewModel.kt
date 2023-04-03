package com.example.yalla.ui.nav.restaurants.restaurant_menu.dish

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.YallaApplication
import com.example.yalla.models.AdditionForRv
import com.example.yalla.models.Dish
import com.example.yalla.models.order.OrderItem
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import java.util.*

class DishViewModel : ViewModel() {
    private var currentDay =
        Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    private lateinit var _chosenDish: Dish
    val chosenDish: Dish
        get() = _chosenDish

    fun setDish(dish: Dish) {
        _chosenDish = dish
    }

    fun getAdditionsForRvByDishId(id: Int): LiveData<List<AdditionForRv>> =
        YallaApplication.repository.getAdditionsForRvByDishId(id)


    private var dishCount = 1
    private var _dishCountLive = MutableLiveData(dishCount)
    val dishCountLive: LiveData<Int>
        get() = _dishCountLive


    private val chosenAdditions = mutableListOf<AdditionForRv>()
    private var _chosenAdditionsLive = MutableLiveData(mutableListOf<AdditionForRv>().toList())
    val chosenAdditionsLive: LiveData<List<AdditionForRv>>
        get() = _chosenAdditionsLive

    private lateinit var _chosenRestLive : LiveData<RestaurantForRv>
    val chosenRestLive: LiveData<RestaurantForRv>
        get() = _chosenRestLive

    fun addDishToCounter() {
        dishCount += 1
        _dishCountLive.postValue(dishCount)
    }

    fun removeDishFromCounter() {
        if (dishCount > 1) {
            dishCount -= 1
            _dishCountLive.postValue(dishCount)
        }

    }

    fun addAddition(addition: AdditionForRv) {
        chosenAdditions.add(addition)
        _chosenAdditionsLive.postValue(chosenAdditions)
    }

    fun removeAddition(addition: AdditionForRv) {
        chosenAdditions.remove(addition)
        _chosenAdditionsLive.postValue(chosenAdditions)
    }

    fun addOrderItem(dishNotes: String, dinerName: String) {
        val orderItem = OrderItem(chosenDish, chosenAdditions, dishNotes, dinerName, dishCount)
        Log.e("TAG", "addOrderItem: $orderItem")
    }

    fun setRestaurantForRv(chosenDestinationId: Int) {
        _chosenRestLive =
            YallaApplication.repository.getRestaurantForRv(
                chosenDish.restaurantId,
                currentDay,
                chosenDestinationId
            )

    }

}