package com.example.yalla.models.responses

import com.example.yalla.models.DailySchedule
import com.google.gson.annotations.SerializedName

class DailySchedulesResponse(
    @SerializedName("daily_schedule")
    val dailySchedules: List<DailySchedule>
)