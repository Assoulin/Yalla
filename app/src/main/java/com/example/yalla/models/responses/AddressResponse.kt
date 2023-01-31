package com.example.yalla.models.responses

import com.example.yalla.models.Address
import com.google.gson.annotations.SerializedName

class AddressResponse(
    @SerializedName("address")
    val addresses:List<Address>
) {
}