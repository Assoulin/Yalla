package com.example.yalla.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class DailySchedule(
    @SerializedName("schedule_id")
    @PrimaryKey
    val scheduleId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("day_of_week")
    val dayOfWeek: Int,
    @SerializedName("opening_hour")
    //Should we change the type to LocalTime?
    val openingHour: String,
    @SerializedName("closing_hour")
    val closingHour: String,
)