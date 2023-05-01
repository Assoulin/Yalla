package com.example.yalla.utils

import android.view.LayoutInflater
import android.view.View
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.models.Destination

fun MainActivity.showArrowBack() {
    binding.arrowBack.visibility = View.VISIBLE
}

fun MainActivity.hideArrowBack() {
    binding.arrowBack.visibility = View.GONE
}
fun MainActivity.hideTopBar() {
    binding.llTopLine.visibility = View.GONE
}
fun MainActivity.showTopBar() {
    binding.llTopLine.visibility = View.VISIBLE
}



fun MainActivity.showBnv() {
    binding.navView.visibility = View.VISIBLE
}

fun MainActivity.hideBnv() {
    binding.navView.visibility = View.GONE
}

fun MainActivity.initTvDestination(destination: String) {
    binding.tvDestination.text = getString(R.string.delivery_to, destination)
    binding.llTopLine.visibility = View.VISIBLE
}

fun MainActivity.hideTopLine() {
    binding.llTopLine.visibility = View.GONE
}

fun MainActivity.showTopLine() {
    binding.llTopLine.visibility = View.VISIBLE
}


val MainActivity.appChosenDestination: Destination?
    get() = chosenDestination

fun MainActivity.setChosenDestination(chosenDest: Destination) {
    chosenDestination = chosenDest
}

val MainActivity.getChosenDestinationId: Int
    get() = chosenDestination!!.destinationId

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun MainActivity.hideCartBtn() {
    binding.btnGoToCart.visibility = View.GONE

}

fun MainActivity.showCartBtn() {
    binding.btnGoToCart.visibility = View.VISIBLE
}



