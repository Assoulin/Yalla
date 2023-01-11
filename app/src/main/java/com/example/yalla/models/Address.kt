package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
@Entity
data class Address(
    @SerializedName("destination_id")
    val destinationId: Int,
    val entrance: String?,
    @SerializedName("house_number")
    val houseNumber: Int=0,
    val apartment:Int=0,
    @SerializedName("location_comment")
    val locationComment: String,
    val street: String,
    @SerializedName("address_id")
    @PrimaryKey(autoGenerate = true)
    val addressId: Int=0,
)

data class AddressOrders(
    @Embedded
    val address: Address,
    @Relation(
        parentColumn = "addressId",
        entityColumn = "orderId"
    )
    val orders: List<Order>
)
data class AddressCustomers(
    @Embedded
    val address: Address,
    @Relation(
        parentColumn = "addressId",
        entityColumn = "customerId"
    )
    val customers: List<Customer>
)
