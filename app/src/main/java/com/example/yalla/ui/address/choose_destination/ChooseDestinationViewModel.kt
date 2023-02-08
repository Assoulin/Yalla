package com.example.yalla.ui.address.choose_destination

import android.app.Application
import androidx.lifecycle.*
import com.example.yalla.YallaApplication


import com.example.yalla.models.Destination
import com.example.yalla.utils.BaseViewModel
import kotlinx.coroutines.launch

const val NO_INTERNET = 1
const val HAS_INTERNET = 2

class ChooseDestinationViewModel : BaseViewModel() {

    val destinationsLive: LiveData<List<Destination>> =
        YallaApplication.repository.getDestinations()
}