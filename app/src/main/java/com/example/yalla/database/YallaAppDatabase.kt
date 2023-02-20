package com.example.yalla.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yalla.dao.RestaurantDao
import com.example.yalla.models.*
import com.example.yalla.models.x_retrofit_models.LikedRestaurant


const val DB_VERSION = 2
const val DB_NAME = "YallaDatabase"
@Database(
    entities = [
        Destination::class,
        Restaurant::class,
        DailySchedule::class,
        DestinationRestaurant::class,
        Dish::class,
        Addition::class,
        DishAddition::class,
        AdditionDetail::class,
        MenuTitle::class,
        Customer::class,
        Address::class,
        Order::class,
        OrderDetail::class,
        FullAddressRoom::class,
        LikedRestaurant::class
    ],

    version = DB_VERSION
)
abstract class YallaAppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        fun create(context: Context): YallaAppDatabase =
            Room.databaseBuilder(context, YallaAppDatabase::class.java, DB_NAME)
                //Reinstalling the DB after a new version appliance.
                .fallbackToDestructiveMigration()
                .build()

    }
}