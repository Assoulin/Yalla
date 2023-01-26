package com.example.yalla.ui.nav.choose_destination

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.yalla.YallaApplication
import com.example.yalla.YallaApplication.Companion.repository

import com.example.yalla.models.Destination
import kotlinx.coroutines.launch

const val NO_INTERNET = 1

class ChooseDestinationViewModel(application: Application) : AndroidViewModel(application) {
    //    private val repository : YallaRepository = YallaRepository(YallaService.create())
//    private lateinit var yallaRoomRepository: YallaRoomRepository
   val destinationsLive: LiveData<List<Destination>> = YallaApplication.repository.getDestinations()

    private val _errors: MutableLiveData<Int> = MutableLiveData()
    val errors: LiveData<Int> = _errors

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    init {
        viewModelScope.launch {
            if (YallaApplication.networkStatusChecker.hasInternet()) {
                _loading.value = true
                repository.refreshDestinations()
                _loading.value = false

            } else {
                _errors.value = NO_INTERNET
            }
        }
    }

    fun manageInternetAvailability() {
        viewModelScope.launch {
            if (YallaApplication.networkStatusChecker.hasInternet()) {
                _loading.value = true
                repository.refreshDestinations()
                _loading.value = false

            } else {
                _errors.value = NO_INTERNET
            }
        }
    }
}