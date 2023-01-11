package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Employee(
    @SerializedName("employee_id")
    @PrimaryKey
    val employeeId: Int,
    @SerializedName("employee_name")
    val employeeName: String,
    @SerializedName("employee_phone_number")
    val employeePhoneNumber: String
)
data class EmployeeOrders(
    @Embedded
    val employee: Employee,
    @Relation(
        parentColumn = "employeeId",
        entityColumn = "orderId"
    )
    val orders: List<Order>
)