package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yalla.R

class RestaurantsFragment : Fragment() {

    companion object {
        fun newInstance() = RestaurantsFragment()
    }

    private lateinit var viewModel: RestaurantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RestaurantsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_restaurants, container, false)
    }
}