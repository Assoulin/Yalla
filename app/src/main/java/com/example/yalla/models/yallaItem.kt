package com.example.yalla.models


import com.google.gson.annotations.SerializedName

data class yallaItem(
    val additions: List<Addition>?,
    @SerializedName("additions_details")
    val additionsDetails: List<AdditionDetails>?,
    val addresses: List<Address>?,
    val customers: List<Customer>?,
    val destinations: List<Destination>?,
    @SerializedName("destinations_restaurants")
    val destinationsRestaurants: List<DestinationsRestaurants>?,
    val dishes: List<Dish>?,
    val employees: List<Employee>?,
    val orders: List<Order>?,
    @SerializedName("orders_details")
    val orderDetails: List<OrderDetails>?,
    val restaurants: List<Restaurant>?,
    @SerializedName("restaurants_schedules")
    val schedules: List<Schedule>?
)