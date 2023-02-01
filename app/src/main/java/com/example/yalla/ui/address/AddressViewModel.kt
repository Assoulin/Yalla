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

    fun makeAndSaveNewAddress(
        chosenDestination: Destination,
        street: String,
        houseNumber: Int,
        entrance: String?,
        apartment: Int?,
        locationInstructions: String?
    ) {
        var roomEntrance = entrance
        if (roomEntrance == EMPTY_STRING) {
            roomEntrance = NOT_REQUIRED_STRING
        }
        var roomLocationInstructions = locationInstructions
        if (roomLocationInstructions == EMPTY_STRING) {
            roomLocationInstructions = NOT_REQUIRED_STRING
        }
        //Todo: check if this is the place to conduct a check before creating a new Address Obj
        saveFullAddress(
            chosenDestination, Address(
                destinationId = chosenDestination.destinationId,
                street = street,
                houseNumber = houseNumber,
                entrance = roomEntrance,
                apartment = apartment ?: NOT_REQUIRED_INT,
                locationInstructions = roomLocationInstructions
            )
        )
    }

    private fun saveFullAddress(destination: Destination, address: Address) {

        viewModelScope.launch {
            YallaApplication.repository.insertAddress(address)

            YallaApplication.repository.insertFullAddress(
                FullAddressRoom(address, destination)
            )
        }
    }
}