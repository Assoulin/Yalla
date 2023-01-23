package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class OrderDetail(
    @SerializedName("order_details_id")
    @PrimaryKey
    val orderDetailId: Int,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("additions_price")
    val additionsPrice: Double,
    @SerializedName("base_price")
    val basePrice: Double,
    @SerializedName("diner_name")
    val dinerName: String,
    @SerializedName("dish_id")
    val dishId: Int,
    val quantity: Int,
    val requests: String,
)

//Views
data class AdditionsOfOrderDetail(
    @Embedded
    val orderDetail: OrderDetail,
    @Relation(
        parentColumn = "orderDetailId",
        entityColumn = "additionDetailId"
    )
    val additionDetails: List<AdditionDetail>?
)
