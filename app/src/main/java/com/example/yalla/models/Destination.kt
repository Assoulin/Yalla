package com.example.yalla.models


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class Destination(
    @SerializedName("destination_id")
    @PrimaryKey
    val destinationId: Int,
    @SerializedName("destination_name")
    val destinationName: String,
    @SerializedName("require_address")
    val requireAddress: Boolean,
    )

//View
data class DestinationAddresses(
    @Embedded
    val destination: Destination,
    @Relation(
        parentColumn = "destinationId",
        entityColumn = "addressId"
    )
    val addresses: List<Address>
)