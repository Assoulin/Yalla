package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.utils.BaseFragment
import com.example.yalla.R
import com.example.yalla.adapters.FilterTagsAdapter
import com.example.yalla.adapters.RestaurantAdapter
import com.example.yalla.databinding.FragmentRestaurantsBinding
import com.example.yalla.models.Destination
import com.example.yalla.ui.address.CHOSEN_DESTINATION_TAG
import com.example.yalla.ui.address.choose_destination.HIDE
import com.example.yalla.ui.address.choose_destination.NO_INTERNET
import com.example.yalla.ui.address.choose_destination.SHOW
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson


class RestaurantsFragment : BaseFragment() {

    private lateinit var viewModel: RestaurantsViewModel

    private var _binding: FragmentRestaurantsBinding? = null
    private val binding: FragmentRestaurantsBinding get() = _binding!!
    lateinit var bottomNavView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).apply {
            visibility = View.VISIBLE
        }
        viewModel = ViewModelProvider(this)[RestaurantsViewModel::class.java]
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

        //RV appliances:
        binding.rvRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvCuisineTags.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.getLiveRestaurantsByDestinationId(chosenDestination.destinationId)
            .observe(viewLifecycleOwner) { restaurantsByDestination ->

                val list = mutableSetOf("הכל")
                restaurantsByDestination.restaurants.forEach { restaurant ->
                    list.addAll(restaurant.cuisine.split(", "))
                }

                binding.rvCuisineTags.adapter = FilterTagsAdapter(
                    list.toList()
                )
            }

        viewModel.getRestaurantsForRv(chosenDestination.destinationId)
            .observe(viewLifecycleOwner) { restaurantsForRv ->
                binding.rvRestaurants.adapter =
                    RestaurantAdapter(restaurantsForRv)
            }

        binding.fabSettings.setOnClickListener {
            bottomNavView.visibility = View.GONE
            findNavController().navigate(
                R.id.chooseDestinationFragment,
            )
        }
        viewModel.errors.observe(viewLifecycleOwner) { indicator ->
            if (indicator == NO_INTERNET) {
                binding.rvCuisineTags.visibility = View.INVISIBLE
                binding.rvRestaurants.visibility = View.INVISIBLE
                manageCardErrorVisibility(SHOW)
                mangeTextInTextError()
                buttonTryAgain.setOnClickListener {
                    manageCardErrorVisibility(HIDE)
                    viewModel.manageInternetAvailability()
                }
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                manageProgressLoadingVisibility(SHOW)
            } else {
                manageProgressLoadingVisibility(HIDE)
                binding.rvCuisineTags.visibility = View.VISIBLE
                binding.rvRestaurants.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}