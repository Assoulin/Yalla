package com.example.yalla.ui.nav.choose_address

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yalla.R

class ChooseAddressFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseAddressFragment()
    }

    private lateinit var viewModel: ChooseAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChooseAddressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}