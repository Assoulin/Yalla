package com.example.yalla.models.responses

import com.example.yalla.models.Dish
import com.google.gson.annotations.SerializedName


class DishResponse(
    @SerializedName("dish")
    val dishes: List<Dish>
)