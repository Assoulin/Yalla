package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import com.example.yalla.models.RestaurantForRv
import com.example.yalla.ui.address.CHOSEN_DESTINATION_TAG
import com.example.yalla.ui.address.choose_destination.HIDE
import com.example.yalla.ui.address.choose_destination.NO_INTERNET
import com.example.yalla.ui.address.choose_destination.SHOW
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

const val DELIMITER = ", "

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
        initViews(chosenDestination)

        viewModel.getRestaurantsForRv(chosenDestination.destinationId)
            .observe(viewLifecycleOwner) { restaurantsForRv ->
                buildRvs(restaurantsForRv)
                binding.clearSelection.setOnClickListener {
                    buildRvs(restaurantsForRv)
                }
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
                with(binding) {
                    tvTopSep.visibility = View.VISIBLE
                    rvCuisineTags.visibility = View.VISIBLE
                    clearSelection.visibility = View.VISIBLE
                    tvBottomSep.visibility = View.VISIBLE
                    rvRestaurants.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun initViews(chosenDestination: Destination) {
        binding.tvDestination.text =
            getString(R.string.delivery_to, chosenDestination.destinationName)

        //RV appliances:
        binding.rvRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvCuisineTags.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun buildRvs(restaurantsForRv: List<RestaurantForRv>) {
        binding.rvRestaurants.adapter =
            RestaurantAdapter(restaurantsForRv){
                //Todo: change icon color to "#F0BD23" and save to room.
            }

        val cuisineTagsMap = getCuisineTagsMap(restaurantsForRv)

        val mutableRestaurantsForRv = mutableSetOf<RestaurantForRv>()

        binding.rvCuisineTags.adapter = FilterTagsAdapter(
            cuisineTagsMap.keys.toList()
        ) {
            cuisineTagsMap[it.first] = it.second
            resLoop@
            for (res in restaurantsForRv) {
                val cuisines = res.cuisine.split(DELIMITER)
                for (i in 0 until (cuisines.size)) {
                    if (cuisineTagsMap[cuisines[i]] == true) {
                        mutableRestaurantsForRv.add(res)
                        continue@resLoop
                    } else if (i == cuisines.size - 1) {
                        mutableRestaurantsForRv.remove(res)
                    }
                }
            }
            binding.rvRestaurants.adapter =
                RestaurantAdapter(mutableRestaurantsForRv.toList()){
                    //Todo: change icon color to "#F0BD23" and save to room.
                }
        }
    }

//    private fun navigateToResFrag(): (RestaurantForRv) -> Unit {
//
//    }

    private fun getCuisineTagsMap(restaurantsForRv: List<RestaurantForRv>): MutableMap<String, Boolean> {
        val set = mutableSetOf<String>()
        val map = mutableMapOf<String, Boolean>()
        restaurantsForRv.forEach { restaurant ->
            set.addAll(restaurant.cuisine.split(DELIMITER))
        }
        for (s in set) {
            map[s] = false
        }
        return map
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}