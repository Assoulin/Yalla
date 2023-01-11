package com.example.yalla.models



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Order(
    @SerializedName("order_id")
    @PrimaryKey
    val orderId: Int,
    @SerializedName("address_id")
    val addressId: Int,
    @SerializedName("contact_number")
    val contactNumber: String,
    @SerializedName("customer_id")
    val customerId: Int,
    @SerializedName("employee_id")
    val employeeId: Int,
    @SerializedName("is_cash")
    val isCash: Boolean,
    @SerializedName("is_delivery")
    val isDelivery: Boolean,
    @SerializedName("order_date")
    val orderDate: String,
    @SerializedName("order_time")
    val orderTime: String,
    @SerializedName("payment_authorization")
    val paymentAuthorization: String,
    @SerializedName("total_order_price")
    val totalOrderPrice: Double
)



