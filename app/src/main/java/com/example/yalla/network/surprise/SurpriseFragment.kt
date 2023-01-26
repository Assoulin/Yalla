package com.example.yalla.network.surprise

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yalla.R

class SurpriseFragment : Fragment() {

    companion object {
        fun newInstance() = SurpriseFragment()
    }

    private lateinit var viewModel: SurpriseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SurpriseViewModel::class.java)
        return inflater.inflate(R.layout.fragment_surprise, container, false)
    }

}