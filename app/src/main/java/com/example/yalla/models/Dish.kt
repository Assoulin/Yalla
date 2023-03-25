package com.example.yalla.models


import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat


@Entity
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
    @SerializedName("group_tag")
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
    ) {
    @Ignore
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


@Entity(tableName = "dish_Additions", primaryKeys = ["dishId", "additionId"])
data class DishAddition(
    @SerializedName("dish_id")
    val dishId: Int,
    @SerializedName("addition_id")
    @ColumnInfo(index = true)
    val additionId: Int,
    @SerializedName("extra_price")
    val extraPrice: Double,
)


data class AdditionsByDish(
    @Embedded
    val dish: Dish,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "additionId",
        associateBy = Junction(DishAddition::class)
    )
    val additions: List<Addition>
)

















