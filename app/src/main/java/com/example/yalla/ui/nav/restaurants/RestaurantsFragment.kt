package com.example.yalla.ui.nav.restaurants

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.adapters.FilterTagsAdapter
import com.example.yalla.adapters.RestaurantAdapter
import com.example.yalla.databinding.FragmentRestaurantsBinding
import com.example.yalla.models.Destination
import com.example.yalla.models.x_retrofit_models.LikedRestaurant
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.ui.address.choose_destination.HIDE
import com.example.yalla.ui.address.choose_destination.NO_INTERNET
import com.example.yalla.ui.address.choose_destination.SHOW
import com.example.yalla.utils.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

const val DELIMITER = ", "
const val CHOSEN_RESTAURANT = "chosen restaurant"

class RestaurantsFragment : BaseFragment() {

    private lateinit var viewModel: RestaurantsViewModel
    private var _binding: FragmentRestaurantsBinding? = null
    private val binding: FragmentRestaurantsBinding get() = _binding!!
    private var yallaActivity: MainActivity? = null
    private lateinit var restaurantsForRv: List<RestaurantForRv>

    //Updatable Restaurants list for filtering purposes.
    private var mutableRestaurantsForRv: MutableSet<RestaurantForRv> = mutableSetOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[RestaurantsViewModel::class.java]
        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yallaActivity = (requireActivity() as MainActivity)
        yallaActivity!!.hideArrowBack()
        yallaActivity!!.showBnv()
        //rebuild the Destination object:
        val chosenDestination = Gson().fromJson(
            (requireActivity() as MainActivity).chosenDestinationJson, Destination::class.java
        )
        //Get the original restaurants for the rv
        viewModel.getRestaurantsForRv(chosenDestination.destinationId)
            .observe(viewLifecycleOwner) {
                restaurantsForRv = it
                buildRvs()
            }

        //init rvs and destination label:
        initViews()
        clearSelectionClickedHandler()

        viewModel.errors.observe(viewLifecycleOwner) { indicator ->
            if (indicator == NO_INTERNET) {
                binding.rvCuisineTags.visibility = View.INVISIBLE
                binding.clearSelectionFrame.visibility = View.INVISIBLE
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
                    rvCuisineTags.visibility = View.VISIBLE
                    clearSelectionFrame.visibility = View.VISIBLE
                    rvRestaurants.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun clearSelectionClickedHandler() {
        binding.clearSelection.setOnClickListener {
            handleLikedRestaurantRoomUpdate()
            buildRvs()
            Snackbar.make(
                requireView(),
                getString(R.string.selection_cleared),
                Snackbar.ANIMATION_MODE_SLIDE
            ).show()
        }
    }

    private fun handleLikedRestaurantRoomUpdate() {
        viewModel.liveLikedRestaurantsOriginal.observe(viewLifecycleOwner) { originalLikes ->
            viewModel.currentChangesInLikedRestaurants.observe(viewLifecycleOwner) { updatedLikes ->
                viewModel.insertLikedRestaurantsToRoom(updatedLikes - originalLikes.toSet())
                viewModel.deleteUnlikedRestaurantsFromRoom(originalLikes - updatedLikes.toSet())
            }
        }
    }

    private fun initViews() {
//        binding.tvDestination.text =
//            getString(R.string.delivery_to, chosenDestination.destinationName)
        //RV appliances:
        binding.rvRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvCuisineTags.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun buildRvs() {
        viewModel.liveLikedRestaurantsOriginal.observe(viewLifecycleOwner) { likedRestaurants ->
            viewModel.initCurrentChangesInLikedRestaurants(likedRestaurants)
            restaurantsRvBuilder(likedRestaurants, restaurantsForRv)
        }
        //Map of cuisine tags and filter status as boolean
        val cuisineTagsMap = getCuisineTagsMap()

        binding.rvCuisineTags.adapter = FilterTagsAdapter(
            cuisineTagsMap.keys.toList(),
            cuisineTagClickHandler(cuisineTagsMap)
        )
    }

    private fun cuisineTagClickHandler(
        cuisineTagsMap: MutableMap<String, Boolean>
    ): (Pair<String, Boolean>) -> Unit = { clickedTag ->
        handleLikedRestaurantRoomUpdate()
        cuisineTagsMap[clickedTag.first] = clickedTag.second
        //scans the original Restaurants list to check whether the restaurant's cuisine list- contains one of the chosen tags.
        filterRestaurantsByTag(cuisineTagsMap)

        if (mutableRestaurantsForRv.isNotEmpty()) {
            viewModel.liveLikedRestaurantsOriginal.observe(viewLifecycleOwner) { likedRestaurants ->
                restaurantsRvBuilder(likedRestaurants, mutableRestaurantsForRv.toList())
            }
        } else {
            buildRvs()
            Snackbar.make(
                requireView(),
                getString(R.string.selection_cleared),
                Snackbar.ANIMATION_MODE_SLIDE
            ).show()
        }
    }

    private fun filterRestaurantsByTag(cuisineTagsMap: MutableMap<String, Boolean>) {
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
    }

    private fun restaurantsRvBuilder(
        likedRestaurants: List<LikedRestaurant>,
        restaurants: List<RestaurantForRv>
    ) {
        binding.rvRestaurants.adapter =
            RestaurantAdapter(
                restaurants,
                likedRestaurants,
                navigateToResFrag(),
                handleLikeButtonClicked()
            )
        binding.rvRestaurants.scrollToPosition(0)
    }


    private fun handleLikeButtonClicked(): (Int, Boolean) -> Unit = { id, isLikedStatus ->
        val currentLikedRestaurant = LikedRestaurant(id)
        if (isLikedStatus) {
            viewModel.addLikedRestaurant(currentLikedRestaurant)
        } else {
            viewModel.removeLikedRestaurant(currentLikedRestaurant)
        }
    }

    private fun navigateToResFrag(): (RestaurantForRv) -> Unit = {
        val bundle = Bundle()
        bundle.putParcelable(CHOSEN_RESTAURANT, it)
        findNavController().navigate(R.id.action_navigation_restaurants_to_restaurantMenu, bundle)
    }

    private fun getCuisineTagsMap(): MutableMap<String, Boolean> {
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

    override fun onPause() {
        super.onPause()
        handleLikedRestaurantRoomUpdate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        yallaActivity = null
    }
}