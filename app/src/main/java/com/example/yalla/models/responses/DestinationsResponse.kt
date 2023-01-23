package com.example.yalla.models.responses

import com.example.yalla.models.Destination
import com.google.gson.annotations.SerializedName

class DestinationsResponse(
    @SerializedName("destination")
    val destinations: List<Destination>
)