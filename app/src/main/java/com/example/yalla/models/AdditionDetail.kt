package com.example.yalla.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//פרטי תוספת למנה
@Entity
data class AdditionDetail(
    @SerializedName("addition_details_id")
    @PrimaryKey
    val additionDetailId: Int,

    //OrderDetails
    @SerializedName("order_details_id")
    val orderDetailId: Int,
    //Addition
    @SerializedName("addition_id")
    val additionId: Int,
    val quantity: Int = 1,
    @SerializedName("total_price")
    val totalPrice: Double = 0.0
)
//{
//    constructor(
//        additionDetailsId: Int,
//        orderDetailsId: Int,
//        additionId: Int,
//        quantity: Int = 1,
//    ) : this(additionDetailsId, orderDetailsId, additionId, quantity, (quantity * additionId))
//}