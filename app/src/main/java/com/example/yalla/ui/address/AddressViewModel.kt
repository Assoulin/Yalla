package com.example.yalla.ui.address

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yalla.YallaApplication
import com.example.yalla.models.Address
import com.example.yalla.models.Destination
import com.example.yalla.models.FullAddressRoom
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {
    //Validations:
    var streetValidationPassedLive = MutableLiveData(false)
    var houseNumberValidationPassedLive = MutableLiveData(false)

    fun makeNewAddress(
        chosenDestination: Destination,
        street: String,
        houseNumber: Int,
        entrance: String,
        apartment: Int,
        locationInstructions: String
    ) {
        saveFullAddress(
            chosenDestination, Address(
                destinationId = chosenDestination.destinationId,
                street = street,
                houseNumber = houseNumber,
                entrance = entrance,
                apartment = apartment,
                locationInstructions = locationInstructions
            )
        )
    }

    private fun saveFullAddress(destination: Destination, address: Address) {
        viewModelScope.launch {
            YallaApplication.repository.insertFullAddress(
                FullAddressRoom(address, destination)
            )
        }
    }
}