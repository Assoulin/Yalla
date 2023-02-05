package com.example.yalla.models

data class RestaurantForRv(
    val restaurant: Restaurant,//
    //Need to upload to git and connect with a service
    val openingHour: String,
    val closingHour: String,
    //need to make a new query for AddressByRestaurant
    val address: Address?,
    //
    val deliveryPrice: String?,//
    val estimatedDeliveryTime: String?,//
//    val isLikedByUser: Boolean = false
) {
    constructor(
        restaurant: Restaurant,
        dailySchedule: DailySchedule,
        address: Address?,
        deliveryPrice: String?,
        estimatedDeliveryTime: String?,
//        isOpenForOrdersMessage: String?,
//        isLikedByUser: Boolean = false
    ) : this(
        restaurant = restaurant,
        openingHour = dailySchedule.openingHour,
        closingHour = dailySchedule.closingHour,
        address = address,
        deliveryPrice = deliveryPrice,
        estimatedDeliveryTime = estimatedDeliveryTime,
//        isOpenForOrdersMessage = estimatedDeliveryTime,
//        isLikedByUser = isLikedByUser
    )
}