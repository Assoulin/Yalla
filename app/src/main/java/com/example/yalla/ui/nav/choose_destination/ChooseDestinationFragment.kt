package com.example.yalla.ui.nav.choose_destination

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yalla.R
import com.example.yalla.adapters.DestinationAdapter
import com.example.yalla.databinding.FragmentChooseDestinationBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.destinationsLive.observe(viewLifecycleOwner) { destinationList ->

            binding.rvDestinations.adapter = DestinationAdapter(destinationList) {
                println("dfgdfg")
            }
        }
        binding.rvDestinations.layoutManager = GridLayoutManager(requireContext(), COLUMN_NUMBER)

        viewModel.errors.observe(viewLifecycleOwner) { indicator ->
            if (indicator == NO_INTERNET) {
                binding.cardError.visibility = View.VISIBLE
                binding.textError.text = getString(R.string.no_internet_connection)
                binding.buttonErrorConfirm.setOnClickListener {
//                    viewModel.manageInternetAvailability()
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