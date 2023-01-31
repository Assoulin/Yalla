package com.example.yalla.models.responses

import com.example.yalla.models.Restaurant
import com.google.gson.annotations.SerializedName

class RestaurantsResponse(
    @SerializedName("restaurant")
    val restaurants:List<Restaurant>
)