package com.example.yalla.models

import com.google.gson.annotations.SerializedName

data class RestaurantForRv(
    val restaurant: Restaurant,
    val openingHour: String,
    val closingHour: String,
    val address: Address?,
    val deliveryPrice: String?,
    val estimatedDeliveryTime: String?,
    val isLikedByUser: Boolean = false
) {
    constructor(
        restaurant: Restaurant,
        dailySchedule: DailySchedule,
        address: Address?,
        deliveryPrice: String?,
        estimatedDeliveryTime: String?,
        isLikedByUser: Boolean = false
    ) : this(
        restaurant = restaurant,
        openingHour = dailySchedule.openingHour,
        closingHour = dailySchedule.closingHour,
        address = address,
        deliveryPrice = deliveryPrice,
        estimatedDeliveryTime = estimatedDeliveryTime,
        isLikedByUser = isLikedByUser
    )
}