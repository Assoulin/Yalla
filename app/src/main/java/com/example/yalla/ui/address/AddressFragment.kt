package com.example.yalla.ui.address

import android.content.Context
import android.graphics.Color
import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.YallaApplication
import com.example.yalla.databinding.FragmentAddressBinding
import com.example.yalla.models.Destination
import com.example.yalla.ui.address.choose_destination.DESTINATION_ID
import com.example.yalla.utils.appChosenDestination


const val CHOSEN_DESTINATION_TAG = "chosenDestination"
const val NOT_REQUIRED_STRING = "-"
const val NOT_REQUIRED_INT = 0
const val EMPTY_STRING = ""

class AddressFragment : Fragment() {
    private lateinit var viewModel: AddressViewModel

    private var _binding: FragmentAddressBinding? = null
    private val binding: FragmentAddressBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //rebuild the Destination object:
        val chosenDestination = (requireActivity() as MainActivity).appChosenDestination!!

        requireAddressDoneListener(chosenDestination)
        editListener()

        binding.tvTitle.text = getString(R.string.adaptive_title, chosenDestination.destinationName)
        if (chosenDestination.requireAddress) {
            binding.tiStreet.editText!!.addTextChangedListener {
                it?.let {
                    val isValid = it.toString().trim().length >= 2
                    viewModel.streetValidationPassedLive.value = isValid
                    if (!isValid) {
                        binding.tiStreet.error = getString(R.string.min_street_length)
                    } else {
                        binding.tiStreet.error = EMPTY_STRING
                    }
                }
            }
            binding.tiHouseNumber.editText!!.addTextChangedListener {
                it?.let { input ->
                    if (input.isEmpty()) {
                        binding.tiHouseNumber.error = getString(R.string.obligated_field)
                    } else {
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
                            requireAddressConfirmListener(chosenDestination)
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
            binding.tiAptNumber.visibility = View.GONE

            notRequireAddressDoneListener(chosenDestination)

        }
    }

    private fun requireAddressConfirmListener(chosenDestination: Destination) {
        binding.btnConfirm.setOnClickListener {
            viewModel.makeAndSaveNewAddress(
                chosenDestination = chosenDestination,
                street = binding.tiStreet.editText!!.text.toString().trim(),
                houseNumber = binding.tiHouseNumber.editText!!.text.toString()
                    .toInt(),
                entrance = binding.tiEntrance.editText?.text?.toString()?.trim(),
                apartment = binding.tiAptNumber.editText!!.text?.toString()?.toIntOrNull(),
                locationInstructions = binding.tiLocationInstructions.editText?.text?.toString()
                    ?.trim()
            )
            Toast.makeText(requireContext(), getString(R.string.order_completed), Toast.LENGTH_LONG)
                .show()
            navigateToHomeFragment(chosenDestination)
            YallaApplication.emptyCart()
        }
    }

    private fun navigateToHomeFragment(chosenDestination: Destination) {
        val bundle = Bundle()
        bundle.putInt(DESTINATION_ID, chosenDestination.destinationId)
        findNavController().navigate(
            R.id.action_addressFragment_to_navigation_home,
            bundle
        )
    }

    private fun editListener() {
        binding.btnEdit.setOnClickListener {
            binding.formCardView.visibility = View.VISIBLE
            binding.cardConfirm.visibility = View.INVISIBLE

        }

    }

    private fun requireAddressDoneListener(chosenDestination: Destination) {
        binding.btnDone.setOnClickListener {
            binding.formCardView.visibility = View.INVISIBLE
            binding.cardConfirm.visibility = View.VISIBLE
            //hide the keyboard
            hideKeyboard()

            //Todo: Add requireAddressValidations()

            binding.tvFullAddress.apply {
                text = getString(
                    R.string.full_address_summary,
                    binding.tiStreet.editText!!.text.toString().trim(),
                    binding.tiHouseNumber.editText!!.text.toString(),
                    chosenDestination.destinationName
                )
            }
            binding.tvApt.apply {
                var aptOrNone =
                    binding.tiAptNumber.editText!!.text.toString().trim()
                if (aptOrNone == "") {
                    aptOrNone = getString(R.string.none)
                }
                text = getString(R.string.apt_summary, aptOrNone)
            }
            binding.tvEntrance.apply {
                var entranceOrNull =
                    binding.tiEntrance.editText!!.text.toString().trim()
                if (entranceOrNull == "") {
                    entranceOrNull = getString(R.string.none)
                }
                text = getString(R.string.entrance_summary, entranceOrNull)
            }
            binding.tvInstructions.apply {
                var instructionsOrNone =
                    binding.tiLocationInstructions.editText!!.text.toString().trim()
                if (instructionsOrNone == "") {
                    instructionsOrNone = getString(R.string.none)
                }
                text = instructionsOrNone
            }

        }
    }

    private fun notRequireAddressDoneListener(chosenDestination: Destination) {
        binding.btnDone.setOnClickListener {
            hideKeyboard()
            binding.formCardView.visibility = View.INVISIBLE
            binding.cardConfirm.visibility = View.VISIBLE

            //Todo: Add notRequireAddressValidations()
            binding.tvFullAddress.text = chosenDestination.destinationName
            binding.tvInstructions.apply {
                var instructionsOrNone =
                    binding.tiLocationInstructions.editText!!.text.toString().trim()
                if (instructionsOrNone == "") {
                    instructionsOrNone = getString(R.string.none)
                }
                text = instructionsOrNone
            }
        }
    }

    private fun hideKeyboard() {
        with(requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager) {
            hideSoftInputFromWindow(requireView().windowToken, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}