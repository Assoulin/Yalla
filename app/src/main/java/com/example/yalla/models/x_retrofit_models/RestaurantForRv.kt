package com.example.yalla.models.x_retrofit_models

import android.os.Build
import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

const val AFTER = "yalla.models.x_retrofit_models.AFTER"
const val BEFORE = "yalla.models.x_retrofit_models.BEFORE"
const val ZONE_ID = "Asia/Jerusalem"

@Parcelize
data class RestaurantForRv(
    val restaurantId: Int,
    val addressId: Int,
    val cuisine: String,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val restaurantName: String,
    val openingHour: String,
    val closingHour: String,
    val destinationId: Int,
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

    fun getOpenStatusMessage(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
            val nowTimeHourMinute: LocalTime =
                LocalTime.now(ZoneId.of(ZONE_ID))
                    .truncatedTo(ChronoUnit.MINUTES)

            return if (nowTimeHourMinute.isAfter(closingHour)) {
                AFTER
            } else if (nowTimeHourMinute.isBefore(openingHour)) {
                BEFORE
            } else {
                closingHour
                    .minusHours(nowTimeHourMinute.hour.toLong())
                    .minusMinutes(nowTimeHourMinute.minute.toLong())
                    .toString()
            }
        } else {
            return openingHours
        }
    }
}