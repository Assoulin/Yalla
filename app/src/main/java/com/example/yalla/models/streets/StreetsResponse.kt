package com.example.yalla.models.streets


import com.google.gson.annotations.SerializedName

data class StreetsResponse(
    @SerializedName("help")
    val help: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("success")
    val success: Boolean
)