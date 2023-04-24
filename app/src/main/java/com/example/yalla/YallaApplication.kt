package com.example.yalla

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import com.example.yalla.database.YallaAppDatabase
import com.example.yalla.models.order.CartItem
import com.example.yalla.network.NetworkStatusChecker
import com.example.yalla.repository.YallaRepository
import com.example.yalla.services.YallaService

class YallaApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        //start analytics
        instance = this
    }

    companion object {
        private var itemsInCart = mutableListOf<CartItem>()
        private var cartLive = MutableLiveData<List<CartItem>>(itemsInCart)
        val getCartLive
            get() = cartLive

        private lateinit var instance: YallaApplication

        fun addToCart(cartItem: CartItem) {
            itemsInCart.add(cartItem)
            cartLive.postValue(itemsInCart)
        }

        fun removeFromCart(cartItem: CartItem) {
            itemsInCart.remove(cartItem)
            cartLive.postValue(itemsInCart)
        }

        fun emptyCart() {
            itemsInCart= mutableListOf()
            cartLive.postValue(itemsInCart)
        }

        val yallaAppDatabase: YallaAppDatabase by lazy {
            YallaAppDatabase.create(instance)
        }
        val repository: YallaRepository by lazy {
            YallaRepository(yallaAppDatabase.restaurantDao())
        }

        val yallaService: YallaService by lazy {
            YallaService.create()
        }

        val networkStatusChecker: NetworkStatusChecker by lazy {
            val connectivityManager = instance.getSystemService(ConnectivityManager::class.java)
            NetworkStatusChecker(connectivityManager)
        }
    }
}