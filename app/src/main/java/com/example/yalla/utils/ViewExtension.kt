package com.example.yalla.utils

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.google.android.material.bottomnavigation.BottomNavigationView


val MainActivity.isArrowVisible: Boolean
    get() = binding.arrowBack.isVisible

fun MainActivity.showArrowBack() {
    binding.arrowBack.visibility = View.VISIBLE
}

fun MainActivity.hideArrowBack() {
    binding.arrowBack.visibility = View.GONE
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


fun MainActivity.setChosenDestinationJson(destJson: String) {
    chosenDestinationJson = destJson
}


val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)



