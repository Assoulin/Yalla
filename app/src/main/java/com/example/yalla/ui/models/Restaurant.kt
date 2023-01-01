package com.example.yalla.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.net.URL
import java.time.LocalTime

@Entity
data class Restaurant(
    @SerializedName("restaurant_id")
    @PrimaryKey
    val restaurantId:Int,
    @SerializedName("restaurant_name")
    val restaurantName: String,

    val description: String,
    @SerializedName("image_url")
    val imageURL: URL,

    @SerializedName("is_active")
    val isActive: Boolean,

    val openingHours:Int,

    val cuisine: Int,

)