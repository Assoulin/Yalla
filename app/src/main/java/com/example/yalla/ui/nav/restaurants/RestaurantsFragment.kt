package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.R
import com.example.yalla.adapters.RestaurantAdapter
import com.example.yalla.databinding.FragmentRestaurantsBinding
import com.example.yalla.models.Destination
import com.example.yalla.ui.address.CHOSEN_DESTINATION_TAG
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class RestaurantsFragment : Fragment() {

    private lateinit var viewModel: RestaurantsViewModel

    private var _binding: FragmentRestaurantsBinding? = null
    private val binding: FragmentRestaurantsBinding get() = _binding!!
    lateinit var  bottomNavView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).apply {
            visibility= View.VISIBLE
        }
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
        binding.tvDestination.text =
            getString(R.string.delivery_to, chosenDestination.destinationName)

        //RV appliance:
        binding.rvRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.getLiveRestaurantsByDestinationId(chosenDestination.destinationId)
            .observe(viewLifecycleOwner) { RestaurantForRv ->
                println(RestaurantForRv)
                binding.rvRestaurants.adapter =
                    RestaurantAdapter(RestaurantForRv.restaurants)
            }

    }
}