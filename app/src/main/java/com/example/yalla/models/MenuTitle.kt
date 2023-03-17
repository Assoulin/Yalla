package com.example.yalla.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class MenuTitle(
    @SerializedName("menu_title_id")
    @PrimaryKey
    val menuTitleId: Int,
    @SerializedName("restaurant_id")
    val restaurantId:Int,
    @SerializedName("title_name")
    val titleName:String,
    @SerializedName("title_description")
    val titleDescription:String,
)
//Views
data class MenuTitleDishes(
    @Embedded
    val menuTitle: MenuTitle,
    @Relation(
        parentColumn = "menuTitleId",
        entityColumn = "menuTitleId"
    )
    val dishes: List<Dish>
)