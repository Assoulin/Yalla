package com.example.yalla.models.responses

import com.example.yalla.models.Addition
import com.google.gson.annotations.SerializedName


class AdditionResponse(
    @SerializedName("addition")
    val additions: List<Addition>
)