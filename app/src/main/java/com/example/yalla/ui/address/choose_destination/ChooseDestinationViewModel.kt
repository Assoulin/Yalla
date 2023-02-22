package com.example.yalla.ui.address.choose_destination

import androidx.lifecycle.*
import com.example.yalla.YallaApplication


import com.example.yalla.models.Destination
import com.example.yalla.utils.BaseViewModel

const val NO_INTERNET = 1
const val HAS_INTERNET = 2

class ChooseDestinationViewModel : BaseViewModel() {

    val destinationsLive: LiveData<List<Destination>> =
        YallaApplication.repository.getDestinations()

    override suspend fun refresh() {
        YallaApplication.repository.chooseDestinationRefreshRoomFromAPI()
    }
}