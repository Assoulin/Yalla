package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat


@Entity
data class Dish(
    @PrimaryKey
    @SerializedName("dish_id")
    val dishId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    val available: Boolean,
    @SerializedName("category_tag")
    val categoryTag: String,
    val course: String,
    val description: String,
    @SerializedName("dish_name")
    val dishName: String,
    @SerializedName("group_tag")
    val groupTag: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("kosher_tag")
    val kosherTag: String,
    val price: Double,
    @SerializedName("require_quantity")
    val requireQuantity: Boolean,

    ) {
    fun priceToString(): String =
        DecimalFormat("#.##").format(price) + "₪"
}

data class DishWithOrderDetails(
    @Embedded
    val dish: Dish,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "orderDetailsId"
    )
    val orderDetails: List<OrderDetails>
)

















