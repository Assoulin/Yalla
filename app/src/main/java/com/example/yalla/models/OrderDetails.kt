package com.example.yalla.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class OrderDetails(
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("order_details_id")
    @PrimaryKey
    val orderDetailsId: Int,
    @SerializedName("additions_price")
    val additionsPrice: Double,
    @SerializedName("base_price")
    val basePrice: Double,
    @SerializedName("diner_name")
    val dinerName: String,
    @SerializedName("dish_id")
    val dishId: Int,
    val quantity: Int,
    val requests: String
)
