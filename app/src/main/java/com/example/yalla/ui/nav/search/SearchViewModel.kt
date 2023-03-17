package com.example.yalla.ui.nav.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yalla.models.x_retrofit_models.RestaurantForRv
import com.example.yalla.utils.BaseViewModel

class SearchViewModel : BaseViewModel() {
    private lateinit var allRestaurants: LiveData<List<RestaurantForRv>>
    private val _text = MutableLiveData<String>().apply {
        value = "חפשו ב-Yalla…"
    }
    val text: LiveData<String> = _text

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }
}