package com.example.yalla.ui.nav.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.adapters.HotOffersAdapter
import com.example.yalla.adapters.HotRestaurantsAdapter
import com.example.yalla.adapters.LikedRestaurantsAdapter
import com.example.yalla.databinding.HomeFragmentBinding
import com.example.yalla.ui.address.choose_destination.DESTINATION_ID

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init rvs:
        initRvs()
        val chosenDestId = arguments?.getInt(DESTINATION_ID)!!

        homeViewModel.hotRestaurants(chosenDestId)
            .observe(viewLifecycleOwner) { hotRestaurants ->
                binding.rvHotRestaurants.adapter = HotRestaurantsAdapter(hotRestaurants)
            }

        homeViewModel.likedRestaurants(chosenDestId)
            .observe(viewLifecycleOwner) { likedRestaurants ->
                if (likedRestaurants.isNotEmpty()) {
                    binding.rvLikedRestaurants.adapter = LikedRestaurantsAdapter(likedRestaurants)
                } else {
                    binding.tvLikedRestaurantsTitle.visibility = View.GONE
                }

            }
        //Todo: 1. Change some dishes to be promoted
        //Todo: 2. Add param for promotion_discount_percent
        //Todo: 3. Add is_business_meal
        //Todo: 4 Add optionals: serve_start and serve_end
        homeViewModel.hotOffers(chosenDestId)
            .observe(viewLifecycleOwner) { hotOffers ->
                binding.rvHotOffers.adapter = HotOffersAdapter(hotOffers){

                }
            }

    }

    private fun initRvs() {
        binding.rvHotRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHotOffers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLikedRestaurants.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}