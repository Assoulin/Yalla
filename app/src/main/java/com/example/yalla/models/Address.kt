package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
@Entity
data class Address(
    @SerializedName("address_id")
    @PrimaryKey(autoGenerate = true)
    val addressId: Int=0,
    @SerializedName("destination_id")
    val destinationId: Int,
    val street: String,
    @SerializedName("house_number")
    val houseNumber: Int?,
    val entrance: String?,
    val apartment:Int?,
    @SerializedName("location_comment")
    val locationInstructions: String?,
)

data class AddressOrders(
    @Embedded
    val address: Address,
    @Relation(
        parentColumn = "addressId",
        entityColumn = "orderId"
    )
    val orders: List<Order>?
)
data class AddressCustomers(
    @Embedded
    val address: Address,
    @Relation(
        parentColumn = "addressId",
        entityColumn = "customerId"
    )
    val customers: List<Customer>?
)
