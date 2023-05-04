package com.example.yalla.models.x_retrofit_models

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.parcelize.Parcelize
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

const val AFTER = "yalla.models.x_retrofit_models.AFTER"
const val BEFORE = "yalla.models.x_retrofit_models.BEFORE"
const val CLOSING_SOON = "yalla.models.x_retrofit_models.CLOSING_SOON"
private const val ZONE_ID = "Asia/Jerusalem"

@Parcelize
data class RestaurantForRv(
    val restaurantId: Int,
    val cuisine: String,
    val description: String,
    val imageUrl: String,
    val restaurantName: String,
    val openingHour: String,
    val closingHour: String,
    val destinationName: String,
    val street: String,
    val houseNumber: Int?,
    val entrance: String?,
    val apartment: Int?,
    val locationInstructions: String?,
    val deliveryPrice: String?,
    val deliveryTime: Int?,
) : Parcelable {
    val getDeliveryPrice
        get() = "₪$deliveryPrice"

    val openingHours
        get() = "${closingHour.slice(0..4)}-${openingHour.slice(0..4)}"

    @RequiresApi(Build.VERSION_CODES.O)
    fun getOpenStatusMessage(): Pair<String, LocalTime>? {
        val openingHour: LocalTime =
            LocalTime.of(
                openingHour.slice(0..1).toInt(),
                openingHour.slice(3..4).toInt()
            )
        val closingHour: LocalTime =
            LocalTime.of(
                closingHour.slice(0..1).toInt(),
                closingHour.slice(3..4).toInt()
            )
        val currentTime: LocalTime =
            LocalTime.now(ZoneId.of(ZONE_ID))
                .truncatedTo(ChronoUnit.MINUTES)
        if (currentTime.isBefore(openingHour)) {
            return Pair(BEFORE, openingHour)
        } else if (currentTime.isAfter(closingHour)) {
            return Pair(AFTER, closingHour)
        }
        val timeTillClosing = closingHour
            .minusHours(currentTime.hour.toLong())
            .minusMinutes(currentTime.minute.toLong())
        if (timeTillClosing.hour == 0) {
            return Pair(CLOSING_SOON, timeTillClosing)
        }
        return null

    }
}