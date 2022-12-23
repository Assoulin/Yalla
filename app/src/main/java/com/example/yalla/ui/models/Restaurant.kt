package com.example.yalla.ui.models

import java.net.URL

data class Restaurant(
    val name: String,
    val description: String,
    val posterURL: URL,
    val isOn: Boolean,
    val category: List<String>,
    val dishes: List<Dish>,
    val deliveryPrice: Double,
) {
    class Dish(
        val dishName: String,
        val description: String,
        val posterURL: String,
        val price: Double,
        val category: String,
    )
}