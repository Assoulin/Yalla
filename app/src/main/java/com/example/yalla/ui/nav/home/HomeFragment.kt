package com.example.yalla.ui.nav.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.adapters.HotRestaurantsAdapter
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
        binding.recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeViewModel.hotRestaurants(arguments?.getInt(DESTINATION_ID)!!)
            .observe(viewLifecycleOwner) {hotRestaurants->
                binding.recyclerView2.adapter=HotRestaurantsAdapter(hotRestaurants)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}