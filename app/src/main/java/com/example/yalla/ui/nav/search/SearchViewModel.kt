package com.example.yalla.ui.nav.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.models.x_retrofit_models.RestaurantForRv

class SearchViewModel : ViewModel() {
    private lateinit var allRestaurants: LiveData<List<RestaurantForRv>>
    private val _text = MutableLiveData<String>().apply {
        value = "חפשו ב-Yalla…"
    }
    val text: LiveData<String>
        get() = _text

    fun handleTextChanged(text: String) {
        _text.postValue(text)
    }
}