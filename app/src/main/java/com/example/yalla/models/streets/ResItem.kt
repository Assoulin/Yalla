package com.example.yalla.models.streets


import com.google.gson.annotations.SerializedName

data class ResItem(
    @SerializedName("city_code")
    val cityCode: Int,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("official_code")
    val officialCode: Int,
    @SerializedName("rank")
    val rank: Double,
    @SerializedName("region_code")
    val regionCode: Int,
    @SerializedName("region_name")
    val regionName: String,
    @SerializedName("street_code")
    val streetCode: Int,
    @SerializedName("street_name")
    val streetName: String,
    @SerializedName("street_name_status")
    val streetNameStatus: String
)