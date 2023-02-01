package com.example.yalla.models.responses

import com.example.yalla.models.DestinationRestaurant
import com.google.gson.annotations.SerializedName

class DestinationsRestaurantsResponse(
    @SerializedName("destination_restaurant")
    val destinationsRestaurants: List<DestinationRestaurant>
)