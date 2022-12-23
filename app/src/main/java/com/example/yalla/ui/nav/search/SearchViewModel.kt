package com.example.yalla.ui.nav.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "חפשו ב-Yalla…"
    }
    val text: LiveData<String> = _text
}