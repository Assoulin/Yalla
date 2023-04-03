package com.example.yalla.models


import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat


@Entity
@Parcelize
data class Dish(
    @PrimaryKey
    @SerializedName("dish_id")
    val dishId: Int,
    @SerializedName("dish_name")
    val dishName: String,
    val description: String,
    val price: Double,
    //Restaurant
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    //Asian, Italian, Eastern, Sushi, Pizza etc', mainly meant for search!
    @SerializedName("category_tag")
    val categoryTag: String,
    //פרווה, בשרי חלבי וכו'
    @SerializedName("group_tag")
    //
    val groupTag: String,
    @SerializedName("kosher_tag")
    val kosherTag: String,
    //MenuTitle
    @SerializedName("menu_title_id")
    val menuTitleId: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val available: Boolean,
    @SerializedName("require_quantity")
    val requireQuantity: Boolean,
    val promoted: Boolean,
) : Parcelable {
    @Ignore
    @IgnoredOnParcel
    val priceToString =
        DecimalFormat("#.##").format(price) + "₪"
}


//Views
data class DishWithOrderDetails(
    @Embedded
    val dish: Dish,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "orderDetailsId"
    )
    val orderDetails: List<OrderDetail>
)


@Entity(primaryKeys = ["dishId", "additionId"])
data class DishAddition(
    @SerializedName("dish_id")
    val dishId: Int,
    @SerializedName("addition_id")
    @ColumnInfo(index = true)
    val additionId: Int,
    @SerializedName("extra_price")
    val extraPrice: Double,
)


data class AdditionForRv(
    //Addition:
    val additionId: Int,
    val additionName: String,
    private val price: Double,
    val available: Boolean,
    private val extraPrice: Double,
    val dishId: Int
) {
    val totalPrice: Double
        get() = price + extraPrice
}

















