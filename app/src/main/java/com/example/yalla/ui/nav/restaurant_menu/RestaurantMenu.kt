package com.example.yalla.ui.nav.restaurant_menu

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.models.x_retrofit_models.RestaurantForRv

import com.example.yalla.ui.nav.restaurants.CHOSEN_RESTAURANT
import com.example.yalla.utils.showArrowBack

class RestaurantMenu : Fragment() {

    private lateinit var viewModel: ResturantMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ResturantMenuViewModel::class.java]
        return inflater.inflate(R.layout.fragment_restaurant_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myActivity = (requireActivity() as MainActivity)
        myActivity.showArrowBack()
        val chosenRest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(CHOSEN_RESTAURANT, RestaurantForRv::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(CHOSEN_RESTAURANT)
        }
        println(chosenRest.toString())

    }


}