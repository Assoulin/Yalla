package com.example.yalla

import android.app.Application
import android.net.ConnectivityManager
import com.example.yalla.database.YallaAppDatabase
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
        private lateinit var instance: YallaApplication

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