package com.example.yalla.models



import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Order(
    @SerializedName("order_id")
    @PrimaryKey
    val orderId: Int,
    @SerializedName("order_date")
    val orderDate: String,
    @SerializedName("order_time")
    val orderTime: String,
    //Address
    @SerializedName("address_id")
    val addressId: Int=0,
    @SerializedName("is_delivery")
    val isDelivery: Boolean,
    //Employee
    @SerializedName("employee_id")
    val employeeId: Int=0,
    @SerializedName("payment_authorization")
    val paymentAuthorization: String,
    @SerializedName("total_order_price")
    val totalOrderPrice: Double,
    //Customer
    @SerializedName("customer_id")
    val customerId: Int=0,
    @SerializedName("contact_number")
    val contactNumber: String,
    @SerializedName("is_cash")
    val isCash: Boolean?,
)

data class OrderWithOrderDetails(
    @Embedded
    val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderDetailId"
    )
    val orderDetails: List<OrderDetail>
)





