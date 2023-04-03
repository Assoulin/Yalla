package com.example.yalla.ui.nav.restaurants.restaurant_menu

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.adapters.MainMenuAdapter
import com.example.yalla.databinding.FragmentRestaurantMenuBinding
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.ui.nav.restaurants.CHOSEN_RESTAURANT
import com.example.yalla.utils.showArrowBack
import com.squareup.picasso.Picasso

const val CHOSEN_DISH = "yalla.ui.nav.restaurants.restaurant_menu.CHOSEN_DISH"

class FragmentRestaurantMenu : Fragment() {
    private lateinit var viewModel: RestaurantMenuViewModel
    private var _binding: FragmentRestaurantMenuBinding? = null
    private val binding: FragmentRestaurantMenuBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[RestaurantMenuViewModel::class.java]
        _binding = FragmentRestaurantMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myActivity = (requireActivity() as MainActivity)
        myActivity.showArrowBack()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.setChosenRest(
                arguments?.getParcelable(
                    CHOSEN_RESTAURANT,
                    RestaurantForRv::class.java
                )
            )
        } else {
            @Suppress("DEPRECATION")
            viewModel.setChosenRest(arguments?.getParcelable(CHOSEN_RESTAURANT))
        }

        //init the top views
        initRestaurantPoster(viewModel.chosenRest)

        binding.rvMainMenu.layoutManager =
            LinearLayoutManager(requireContext(), VERTICAL, false)

        with(binding) {
            with(viewModel.chosenRest) {
                tvRestaurantName.text = restaurantName
                tvRestaurantDescription.text = description
                tvRestaurantAddress.text =
                    getString(R.string.address, street, houseNumber, destinationName)

                tvOpeningHours2.text = openingHours

                viewModel.getMenuTitleDishesByRestaurantId(restaurantId)
                    .observe(viewLifecycleOwner) { menus ->
                        rvMainMenu.adapter = MainMenuAdapter(menus) { dish ->
                            val bundle = Bundle()
                            bundle.putParcelable(CHOSEN_DISH, dish)
                            findNavController().navigate(
                                R.id.action_restaurantMenu_to_dishFragment,
                                bundle
                            )
                        }
                    }
            }
        }
    }

    private fun initRestaurantPoster(chosenRest: RestaurantForRv) {
        Picasso.get()
            .load(chosenRest.imageUrl)
            .into(binding.ivRestaurantPoster)
    }
}