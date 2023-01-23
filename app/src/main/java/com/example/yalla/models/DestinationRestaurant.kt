package com.example.yalla.models


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["destinationId", "restaurantId"])
data class DestinationRestaurant(
    @SerializedName("destination_id")
    val destinationId: Int,
    @SerializedName("restaurant_id")
    @ColumnInfo(index = true)
    val restaurantId: Int,
    @SerializedName("delivery_time")
    val deliveryTime: Int,
    @SerializedName("delivery_price")
    val deliveryPrice: Int,
){
    fun deliveryPriceToString(): String = "${deliveryPrice}₪"
}



data class RestaurantsByDestination(
    @Embedded
    val destination: Destination,
    @Relation(
        parentColumn = "destinationId",
        entityColumn = "restaurantId",
        associateBy = Junction(DestinationRestaurant::class)
    )
    val restaurants: List<Restaurant>
)