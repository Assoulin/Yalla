package com.example.yalla.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Restaurant(
    @SerializedName("restaurant_id")
    @PrimaryKey
    val restaurantId: Int,
    //Address
    @SerializedName("address_id")
    val addressId: Int,
    //cuisine categories are separated by coma + space (, )
    val cuisine: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("restaurant_name")
    val restaurantName: String,
)

//Views
data class RestaurantDishes(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "dishId"
    )
    val dishes: List<Dish>
)
data class RestaurantMenuTitles(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "menuTitleId"
    )
    val menuTitle: List<MenuTitle>
)
data class RestaurantAddress(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "addressId"
    )
    val address: Address
)

data class RestaurantSchedule(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "scheduleId"
    )
    val dailySchedules: List<DailySchedule>
)