package com.example.yalla.models


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
//פרטי תוספת למנה
@Entity
data class AdditionDetails(
    @SerializedName("addition_id")
    val additionId: Int,
    @SerializedName("order_details_id")
    val orderDetailsId: Int,
    @SerializedName("order_id")
    val orderId: Int,
    val quantity: Int,
    @SerializedName("total_price")
    val totalPrice: Double
)