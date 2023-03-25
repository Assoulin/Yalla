package com.example.yalla.ui.nav.restaurants.restaurant_menu

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.adapters.MainMenuAdapter
import com.example.yalla.databinding.FragmentRestaurantMenuBinding
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.ui.nav.restaurants.CHOSEN_DESTINATION_NAME
import com.example.yalla.ui.nav.restaurants.CHOSEN_RESTAURANT
import com.example.yalla.utils.showArrowBack
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class FragmentRestaurantMenu : Fragment() {
    private lateinit var viewModel: RestaurantMenuViewModel
    private var _binding: FragmentRestaurantMenuBinding? = null
    private val binding: FragmentRestaurantMenuBinding
        get() = _binding!!
    private lateinit var chosenRest: RestaurantForRv
    private lateinit var chosenDestinationName: String

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
        chosenDestinationName = arguments?.getString(CHOSEN_DESTINATION_NAME)!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            chosenRest = arguments?.getParcelable(CHOSEN_RESTAURANT, RestaurantForRv::class.java)!!

        } else {
            @Suppress("DEPRECATION")
            chosenRest = arguments?.getParcelable(CHOSEN_RESTAURANT)!!
        }
        //init the top views
        initRestaurantPoster(chosenRest)

        binding.rvMainMenu.layoutManager =
            LinearLayoutManager(requireContext(), VERTICAL, false)


        with(binding) {
            with(chosenRest) {
                tvRestaurantName.text = restaurantName
                tvRestaurantDescription.text = description
                tvRestaurantAddress.text =
                    getString(R.string.address, street, houseNumber, chosenDestinationName)

                tvOpeningHours2.text = openingHours

                viewModel.getMenuTitleDishesByRestaurantId(chosenRest.restaurantId)
                    .observe(viewLifecycleOwner) {
                        Log.e("tag", it.toString())
                        rvMainMenu.adapter = MainMenuAdapter(it) {

                        }
                    }

            }

        }


    }

    private fun initRestaurantPoster(chosenRest: RestaurantForRv) {
        val target = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                // bitmap
                binding.ivRestaurantPoster.setImageBitmap(bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                binding.ivRestaurantPoster.setImageDrawable(errorDrawable)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                binding.ivRestaurantPoster.setImageDrawable(placeHolderDrawable)
            }
        }
        Picasso.get()
            .load(chosenRest.imageUrl)
            .placeholder(
                ResourcesCompat.getDrawable(
                    resources, R.drawable.proggress, null
                )!!
            )
            .error(
                ResourcesCompat.getDrawable(
                    resources, R.drawable.bag, null
                )!!
            )
            .into(target)
    }
}