package com.example.yalla.utils
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.google.android.material.bottomnavigation.BottomNavigationView


val MainActivity.isArrowVisible: Boolean
    get()= binding.arrowBack.isVisible

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

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)



