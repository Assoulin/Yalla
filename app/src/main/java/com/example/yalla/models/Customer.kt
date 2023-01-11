package com.example.yalla.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Customer(
    //In the UI, Address object must be uploaded before Customer build!
    @SerializedName("address_id")
    val addressId: Int,
    @SerializedName("customer_name")
    val customerName: String,
    val email: String,
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("customer_id")
    @PrimaryKey(autoGenerate = true)
    val customerId: Int,

)

//View
data class CustomerOrders(
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "orderId"
    )
    val reviews: List<Order>?
)
data class CustomerAddress(
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "addressId"
    )
    val customerAddress: Address
)