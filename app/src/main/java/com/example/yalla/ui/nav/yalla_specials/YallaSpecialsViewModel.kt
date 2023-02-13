package com.example.yalla.ui.nav.yalla_specials

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.R



class YallaSpecialsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "בקרוב!"
    }
    val text: LiveData<String> = _text
}