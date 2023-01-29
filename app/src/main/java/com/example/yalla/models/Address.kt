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
    val street: String,
    @SerializedName("house_number")
    val houseNumber: Int?,
    val entrance: String?,
    val apartment:Int?,
    @SerializedName("location_comment")
    val locationInstructions: String?,
    @SerializedName("address_id")
    @PrimaryKey(autoGenerate = true)
    val addressId: Int=0,
)


@Entity(tableName = "full_addresses")
data class FullAddressRoom(
    @PrimaryKey
    val addressId:Int,
    val streetName:String,
    val houseNumber: Int,
    val entrance: String,
    val apartment: Int,
    val destinationName:String,
    val locationInstructions:String,
){
    constructor(address: Address, destination: Destination): this(
        addressId= address.addressId,
        streetName = address.street,
        houseNumber = address.houseNumber!!,
        entrance = address.entrance!!,
        apartment = address.apartment!!,
        destinationName = destination.destinationName,
        locationInstructions = address.locationInstructions!!,
    )
}

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
