package com.example.yalla.ui.address.choose_destination

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yalla.R
import com.example.yalla.adapters.DestinationAdapter
import com.example.yalla.databinding.FragmentChooseDestinationBinding
import com.example.yalla.models.Destination
import com.example.yalla.ui.address.CHOSEN_DESTINATION_TAG
import com.google.gson.Gson

const val COLUMN_NUMBER = 3

class ChooseDestinationFragment : Fragment() {
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

    private val onDestinationSelected: (Destination) -> Unit = { chosenDestination ->
        val json = Gson().toJson(chosenDestination)
        Toast.makeText(
            requireContext(),
            chosenDestination.destinationName,
            Toast.LENGTH_LONG
        ).show()
        findNavController().navigate(
            R.id.action_chooseDestinationFragment_to_addressFragment,
            bundleOf(Pair(CHOSEN_DESTINATION_TAG, json))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.destinationsLive.observe(viewLifecycleOwner) { destinationList ->
            binding.rvDestinations.adapter =
                DestinationAdapter(destinationList, onDestinationSelected)
        }
        binding.rvDestinations.layoutManager = GridLayoutManager(requireContext(), COLUMN_NUMBER)

        viewModel.errors.observe(viewLifecycleOwner) { indicator ->
            if (indicator == NO_INTERNET) {
                binding.rvDestinations.visibility = View.INVISIBLE
                binding.cardError.visibility = View.VISIBLE
                binding.textError.text = getString(R.string.no_internet_connection)
                binding.buttonTryAgain.setOnClickListener {
                    binding.cardError.visibility = View.INVISIBLE
                    viewModel.manageInternetAvailability()
                }
            } else {
                binding.buttonTryAgain.setOnClickListener {
                    binding.rvDestinations.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressLoading.visibility = View.VISIBLE
            } else {
                binding.progressLoading.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}