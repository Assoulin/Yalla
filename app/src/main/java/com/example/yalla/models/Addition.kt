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
    //Dish:
    @SerializedName("addition_name")
    val additionName: String,
    val price: Double,
    val available: Boolean,
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