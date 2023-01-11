package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Destination(
    @SerializedName("destination_name")
    val destinationName: String,

    @SerializedName("destination_id")
    @PrimaryKey
    val destinationId: Int,
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