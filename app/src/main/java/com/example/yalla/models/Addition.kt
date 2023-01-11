package com.example.yalla.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

@Entity
data class Addition(
    @SerializedName("addition_id")
    @PrimaryKey
    val additionId: Int,
    //Dish
    @SerializedName("dish_id")
    val dishId: Int,
    @SerializedName("addition_name")
    val additionName: String,
    val price: Double,
    @SerializedName("image_url")
    val imageUrl: String,

    val available: Boolean,
    @SerializedName("require_quantity")
    val requireQuantity: Boolean,
) {
    fun priceToString(): String =
        DecimalFormat("#.##").format(price) + "₪"

}

//Views
data class OrderDetailsOfAddition(
    @Embedded
    val addition: Addition,
    @Relation(
        parentColumn = "additionId",
        entityColumn = "orderDetailId"
    )
    val orderDetails: List<OrderDetail>
)