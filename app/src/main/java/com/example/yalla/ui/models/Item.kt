package com.example.yalla.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Item(
    @SerializedName("item_id")
    @PrimaryKey
    val itemId: Int,
    @SerializedName("item_name")
    val itemName: String,

    val description: String,
    val price: Double,
    @SerializedName("restaurant_id")
    val restaurantId:Int,
    @SerializedName("image_url")
    val imageURL: String,
    @SerializedName("type_id")
    val typeId:Int,
    @SerializedName("category_id")
    val categoryId:Int,
    @SerializedName("stage_id")
    val stageId:Int,
    val available:Boolean,
    )
