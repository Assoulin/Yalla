package com.example.yalla.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Destination(
    @SerializedName("destination_id")
    @PrimaryKey
    val destinationId:Int,
    @SerializedName("destination_name")
    val destinationName:String,
    @SerializedName("delivery_price")
    val deliveryPrice:Double,
)