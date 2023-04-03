package com.example.yalla.models.responses

import com.example.yalla.models.DishAddition
import com.google.gson.annotations.SerializedName

class DishAdditionResponse(
    @SerializedName("dish_addition")
    val dishAdditions: List<DishAddition>
)
