package com.example.yalla.models


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["destinationId", "restaurantId"])
data class DestinationsRestaurants(
    @SerializedName("destination_id")
    val destinationId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("delivery_price")
    val deliveryPrice: Int,
    @SerializedName("delivery_time")
    val deliveryTime: Int,
)

@Entity(primaryKeys = ["destinationId","restaurantId"])
data class DestinationsRestaurantsCrossRef(
    val destinationId: Int,
    val restaurantId: Int,

    )

data class DestinationRestaurantRelation(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "destinationId",
        associateBy = Junction(DestinationsRestaurantsCrossRef::class)
    )
    val restaurantDestinations: List<Destination>
)
