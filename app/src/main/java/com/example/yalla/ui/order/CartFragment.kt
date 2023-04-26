package com.example.yalla.ui.order

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.YallaApplication
import com.example.yalla.adapters.CartItemAdapter
import com.example.yalla.databinding.FragmentCartBinding
import com.example.yalla.utils.*
import kotlin.streams.toList

class CartFragment : Fragment() {
    private lateinit var viewModel: CartViewModel
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideBnv()
        (requireActivity() as MainActivity).showArrowBack()


        with(binding) {
            rvCart.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            YallaApplication.getCartLive.observe(viewLifecycleOwner) { cartItems ->
                val totalPrice = cartItems.stream().map { c -> c.totalItemPrice }.toList().sum()
                tvTotalPrice.text = totalPrice.toString()
                btnImDone.isClickable = cartItems.isNotEmpty()
                if (btnImDone.isClickable) {
                    handleImDoneBtnClickListener()
                    ivCart.visibility = View.GONE
                    btnImDone.setBackgroundColor(requireContext().getColor(R.color.orange))
                } else {
                    ivCart.visibility = View.VISIBLE
                    btnImDone.setBackgroundColor(requireContext().getColor(R.color.black))
                }
                rvCart.adapter = CartItemAdapter(cartItems) { removeItem ->
                    YallaApplication.removeFromCart(removeItem)
                }
            }
        }

    }

    private fun handleImDoneBtnClickListener() {
        binding.btnImDone.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_addressFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).showCartBtn()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).hideCartBtn()
    }
}