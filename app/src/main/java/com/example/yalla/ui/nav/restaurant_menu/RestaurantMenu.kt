package com.example.yalla.ui.nav.restaurant_menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yalla.R

class RestaurantMenu : Fragment() {

    companion object {
        fun newInstance() = RestaurantMenu()
    }

    private lateinit var viewModel: ResturantMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ResturantMenuViewModel::class.java)
        return inflater.inflate(R.layout.fragment_restaurant_menu, container, false)
    }



}