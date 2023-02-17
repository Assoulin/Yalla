package com.example.yalla.models.x_retrofit_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikedRestaurant(
    @PrimaryKey
    val restaurantId: Int,
)