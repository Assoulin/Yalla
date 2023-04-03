package com.example.yalla.models.order

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.yalla.models.AdditionForRv
import com.example.yalla.models.Dish
import kotlin.streams.toList

data class OrderItem(
    val chosenDish: Dish,
    val additions: List<AdditionForRv>,
    val dishNoted: String,
    val nameForTheItem: String,
    val quantity: Int
) {
    val totalItemPrice: Double
        @RequiresApi(Build.VERSION_CODES.N)
        get() = (chosenDish.price + additions.stream().map { a -> a.totalPrice }.toList()
            .sum()) * quantity
}

