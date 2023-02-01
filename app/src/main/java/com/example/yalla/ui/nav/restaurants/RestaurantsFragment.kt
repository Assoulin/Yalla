package com.example.yalla.ui.nav.restaurants

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.R
import com.example.yalla.adapters.RestaurantAdapter
import com.example.yalla.databinding.FragmentAddressBinding
import com.example.yalla.databinding.FragmentRestaurantsBinding
import com.example.yalla.models.Destination
import com.example.yalla.ui.address.CHOSEN_DESTINATION_TAG
import com.google.gson.Gson

class RestaurantsFragment : Fragment() {

    private lateinit var viewModel: RestaurantsViewModel

    private var _binding: FragmentRestaurantsBinding? = null
    private val binding: FragmentRestaurantsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(RestaurantsViewModel::class.java)
        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //rebuild the Destination object:
        val chosenDestination = Gson().fromJson(
            requireArguments().getString(CHOSEN_DESTINATION_TAG), Destination::class.java
        )

        binding.rvRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.getLiveRestaurantsByChosenDestinationId(chosenDestination.destinationId)
            .observe(viewLifecycleOwner) { restaurantsByDestination ->
                println(restaurantsByDestination)
                binding.rvRestaurants.adapter =
                    RestaurantAdapter(restaurantsByDestination.restaurants)
            }

    }
}