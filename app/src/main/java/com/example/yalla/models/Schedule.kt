package com.example.yalla.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Schedule(
    @SerializedName("schedule_id")
    @PrimaryKey
    val scheduleId: Int,
    @SerializedName("closing_hour")
    val closingHour: String,
    @SerializedName("day_of_week")
    val dayOfWeek: Int,
    @SerializedName("opening_hour")
    val openingHour: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int,

)