package com.example.yalla.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yalla.YallaApplication
import com.example.yalla.ui.address.choose_destination.HAS_INTERNET
import com.example.yalla.ui.address.choose_destination.NO_INTERNET
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    private val _errors: MutableLiveData<Int> = MutableLiveData(HAS_INTERNET)
    val errors: LiveData<Int> = _errors

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading


    init {
        manageInternetAvailability()
    }

    abstract suspend fun refresh()

    fun manageInternetAvailability() {

        viewModelScope.launch {
            if (YallaApplication.networkStatusChecker.hasInternet()) {
                _loading.value = true
                refresh()
                _loading.value = false
                _errors.value = HAS_INTERNET
            } else {
                _errors.value = NO_INTERNET
            }
        }
    }
}