package com.example.yalla.models.responses


import com.example.yalla.models.MenuTitle
import com.google.gson.annotations.SerializedName

class MenuTitleResponse (
    @SerializedName("menu_title")
    val menuTitles: List<MenuTitle>
)
