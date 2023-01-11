package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Restaurant(
    @SerializedName("restaurant_id")
    @PrimaryKey
    val restaurantId: Int,
    @SerializedName("address_id")
    val addressId: Int,
    val cuisine: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_active")
    val isActive: Boolean,

    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("schedule_id")
    val scheduleId: Int
)

//Views
data class RestaurantAddress(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "addressId"
    )
    val address: Address
)

data class RestaurantSchedule(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "scheduleId"
    )
    val schedule: Schedule
)