package com.example.yalla.utils

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.yalla.R


abstract class BaseFragment : Fragment() {

    private lateinit var cardError: CardView
    private lateinit var textError: TextView
    lateinit var buttonTryAgain: Button
    private lateinit var progressLoading: ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardError = view.findViewById(R.id.card_error)
        textError = view.findViewById(R.id.text_error)
        buttonTryAgain = view.findViewById(R.id.button_try_again)
        progressLoading = view.findViewById(R.id.progress_loading)

    }

    fun manageProgressLoadingVisibility(show: Boolean) {
        if (show) {
            progressLoading.visibility = View.VISIBLE
        } else {
            progressLoading.visibility = View.GONE
        }
    }

    fun manageCardErrorVisibility(show: Boolean) {
        if (show) {
            cardError.visibility = View.VISIBLE
        } else {
            cardError.visibility = View.GONE
        }
    }

    fun mangeTextInTextError(text: String = getString(R.string.no_internet_connection)) {
        textError.text = text
    }

}