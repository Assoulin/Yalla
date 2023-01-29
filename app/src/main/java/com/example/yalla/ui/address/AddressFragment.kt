package com.example.yalla.ui.address

import android.graphics.Color
import android.graphics.Color.WHITE
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.yalla.R
import com.example.yalla.databinding.FragmentAddressBinding
import com.example.yalla.models.Destination
import com.google.gson.Gson


const val CHOSEN_DESTINATION_TAG = "chosenDestination"
const val NOT_REQUIRED_STRING = "-"
const val NOT_REQUIRED_INT = 0
const val EMPTY_STRING = ""
class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding: FragmentAddressBinding get() = _binding!!

    private lateinit var viewModel: AddressViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //make the Destination object again:
        val chosenDestination = Gson().fromJson(
            requireArguments().getString(CHOSEN_DESTINATION_TAG), Destination::class.java
        )

        binding.tvTitle.text = getString(R.string.adaptive_title, chosenDestination.destinationName)

        if (chosenDestination.requireAddress) {
            binding.tiStreet.editText!!.addTextChangedListener {
                it?.let {
                     val isValid = it.toString().trim().length >= 2
                    viewModel.streetValidationPassedLive.value = isValid
                    if (!isValid){
                        binding.tiStreet.error = getString(R.string.min_street_length)
                    }else{
                        binding.tiStreet.error = EMPTY_STRING
                    }
                }
            }
            binding.tiHouseNumber.editText!!.addTextChangedListener {
                it?.let { input ->
                    if (input.isEmpty()){
                        binding.tiHouseNumber.error = getString(R.string.obligated_field)
                    }else{
                        binding.tiHouseNumber.error = EMPTY_STRING
                    }
                    viewModel.houseNumberValidationPassedLive.value = input.isNotEmpty()
                }
            }
            viewModel.streetValidationPassedLive.observe(viewLifecycleOwner) { streetValidationPassed ->
                viewModel.houseNumberValidationPassedLive.observe(viewLifecycleOwner) { houseNumberValidationPassed ->
                    with(binding.btnDone) {
                        isClickable = houseNumberValidationPassed && streetValidationPassed
                        if (isClickable) {
                            this.setBackgroundColor(Color.parseColor("#E6F08323"))
                            this.setTextColor(WHITE)
                            binding.btnDone.setOnClickListener {
                                val address = viewModel.makeNewAddress(
                                    chosenDestination = chosenDestination,
                                    street = binding.tiStreet.editText!!.text.toString().trim(),
                                    houseNumber = binding.tiHouseNumber.editText!!.text.toString()
                                        .toInt(),
                                    entrance = binding.tiEntrance.editText!!.text.toString().trim(),
                                    apartment = binding.tiAptNumber!!.editText!!.text.toString()
                                        .toInt(),
                                    locationInstructions = binding.tiLocationInstructions.editText!!
                                        .text.toString()
                                        .trim()
                                )
                            }
                        } else {
                            this.setBackgroundColor(Color.parseColor("#A6F08323"))
                            this.setTextColor(Color.parseColor("#546E7A"))
                        }
                    }
                }
            }
            //Case the destination chosen does not require Address.
        } else {
            with(binding.btnDone) {
                isClickable = true
                setBackgroundColor(Color.parseColor("#E6F08323"))
                setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            binding.tiStreet.visibility = View.GONE
            binding.tiHouseNumber.visibility = View.GONE
            binding.tiEntrance.visibility = View.GONE
            binding.tiAptNumber!!.visibility = View.GONE
            binding.btnDone.setOnClickListener {
                viewModel.makeNewAddress(
                    chosenDestination = chosenDestination,
                    street = NOT_REQUIRED_STRING,
                    houseNumber = NOT_REQUIRED_INT,
                    entrance = NOT_REQUIRED_STRING,
                    apartment = NOT_REQUIRED_INT,
                    locationInstructions = binding.tiLocationInstructions.editText!!.text.toString()
                        .trim()
                )
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}