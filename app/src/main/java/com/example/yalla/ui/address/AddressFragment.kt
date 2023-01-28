package com.example.yalla.ui.address

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.yalla.databinding.FragmentAddressBinding
import com.example.yalla.models.Address
import com.example.yalla.models.Destination
import com.google.gson.Gson


const val CHOSEN_DESTINATION_TAG = "chosenDestination"

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding: FragmentAddressBinding get() = _binding!!

    private lateinit var viewModel: AddressViewModel

    //Validations:
    private var streetValidationPassedLive = MutableLiveData(false)
    private var houseNumberValidationPassedLive = MutableLiveData(false)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chosenDestinationJson = requireArguments().getString(CHOSEN_DESTINATION_TAG)
        val chosenDestination = Gson().fromJson(chosenDestinationJson, Destination::class.java)
        if (chosenDestination.requireAddress) {
            binding.tiStreet.editText!!.addTextChangedListener {
                it?.let {
                    with(it.toString().trim()) {
                        streetValidationPassedLive.value = length >= 2
                    }
                    //Toast.makeText(view.context, streetInput,Toast.LENGTH_SHORT).show()
                }
            }
            binding.tiHouseNumber.editText!!.addTextChangedListener {
                it?.let { input ->
                    houseNumberValidationPassedLive.value = input.isNotEmpty()
                }
            }
            streetValidationPassedLive.observe(viewLifecycleOwner) { streetValidationPassed ->
                if (streetValidationPassed) {
                    houseNumberValidationPassedLive.observe(viewLifecycleOwner) { houseNumberValidationPassed ->
                        with(binding.btnDone) {
                            isClickable = houseNumberValidationPassed
                            if (isClickable) {
                                this.setBackgroundColor(Color.parseColor("#E6F08323"))
                                this.setTextColor(Color.parseColor("#FFFFFFFF"))
                            } else {
                                this.setBackgroundColor(Color.parseColor("#A6F08323"))
                                this.setTextColor(Color.parseColor("#546E7A"))
                            }
                        }
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}