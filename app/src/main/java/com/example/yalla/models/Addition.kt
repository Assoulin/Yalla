package com.example.yalla.models
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

@Entity(primaryKeys = ["additionId","dishId"])
data class  Addition  (
    @SerializedName("addition_id")
    val additionId : Int,
    @SerializedName("dish_id")
    val dishId: Int,
    @SerializedName("addition_name")
    val additionName: String,
    val available: Boolean,
    @SerializedName("image_url")
    val imageUrl: String,
    val price: Double,
    @SerializedName("require_quantity")
    val requireQuantity: Boolean
)
{
   fun priceToString():String=
       DecimalFormat("#.##").format(price)+"₪"

}