package com.example.yalla.models

import android.os.Build
import androidx.room.Ignore
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

const val CLOSED = "closed"
const val WILL_CLOSE_IN = "תסגר בעוד "

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
    val estimatedDeliveryTime: String?,
) {
    @Ignore
    var isLiked = false

    val getDeliveryPrice
        get() = "₪$deliveryPrice"

    val getOpeningStatusMessage
        get() = "${closingHour.slice(0..4)} ~ ${openingHour.slice(0..4)}"

    fun unlike(){
        isLiked = false
    }
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
                LocalTime.now(ZoneId.of("Asia/Jerusalem"))
                    .truncatedTo(ChronoUnit.MINUTES)

            if (nowTimeHourMinute.isAfter(closingHour)) {
                return CLOSED
            } else if (nowTimeHourMinute.isBefore(openingHour)) {
                return CLOSED
            } else {
                return WILL_CLOSE_IN +
                        closingHour
                            .minusHours(nowTimeHourMinute.hour.toLong())
                            .minusMinutes(nowTimeHourMinute.minute.toLong())
                            .toString()
            }
        } else {
            return getOpeningStatusMessage
        }

//        val israelLocale = Locale.Builder().setLanguage("iw").setRegion("IL").build()
//        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", israelLocale)
//        timeFormat.timeZone = TimeZone.getTimeZone("Asia/Jerusalem")
//        val curTime = timeFormat.format(Date())
    }
}