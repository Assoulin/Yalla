package com.example.yalla.ui.address.choose_destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.YallaApplication
import com.example.yalla.adapters.DestinationAdapter
import com.example.yalla.databinding.FragmentChooseDestinationBinding
import com.example.yalla.models.Destination
import com.example.yalla.utils.*

private const val COLUMN_NUMBER = 3
const val SHOW = true
const val HIDE = false
const val DESTINATION_ID = "yalla.ui.address.choose_destination.DESTINATION_ID"

class ChooseDestinationFragment : BaseFragment() {
    private lateinit var viewModel: ChooseDestinationViewModel

    private var _binding: FragmentChooseDestinationBinding? = null

    private val binding: FragmentChooseDestinationBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[ChooseDestinationViewModel::class.java]
        _binding = FragmentChooseDestinationBinding.inflate(inflater, container, false)

        return binding.root
    }

    private val onDestinationSelected: (Destination) -> Unit = { destination ->
        with(requireActivity() as MainActivity) {
            initTvDestination(destination = destination.destinationName)
            if (destination != appChosenDestination) {
                appChosenDestination?.let {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.cart_is_empty),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setChosenDestination(destination)
                YallaApplication.emptyCart()
            }
        }
        val bundle = Bundle()
        bundle.putInt(DESTINATION_ID, destination.destinationId)
        findNavController().navigate(
            R.id.action_chooseDestinationFragment_to_navigation_home,
            bundle
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDestinations.layoutManager = GridLayoutManager(requireContext(), COLUMN_NUMBER)

        viewModel.destinationsLive.observe(viewLifecycleOwner) { destinationList ->

            binding.rvDestinations.adapter =
                DestinationAdapter(destinationList, onDestinationSelected)
        }
        viewModel.errors.observe(viewLifecycleOwner) { indicator ->
            if (indicator == NO_INTERNET) {
                binding.rvDestinations.visibility = View.INVISIBLE
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
                binding.rvDestinations.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with((requireActivity() as MainActivity)) {
            hideBnv()
            hideTopLine()
        }
    }

    override fun onPause() {
        super.onPause()
        with((requireActivity() as MainActivity)) {
            showBnv()
            showTopLine()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}