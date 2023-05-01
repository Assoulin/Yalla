package com.example.yalla.ui.nav.restaurants.restaurant_menu.dish

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yalla.MainActivity
import com.example.yalla.R
import com.example.yalla.adapters.AdditionAdapter
import com.example.yalla.databinding.FragmentDishBinding
import com.example.yalla.models.AdditionForRv
import com.example.yalla.models.Dish
import com.example.yalla.ui.nav.restaurants.CHOSEN_RESTAURANT
import com.example.yalla.ui.nav.restaurants.restaurant_menu.CHOSEN_DISH
import com.example.yalla.utils.*
import com.squareup.picasso.Picasso
import kotlin.streams.toList

const val SPAN_COUNT = 3

class DishFragment : Fragment() {
    private lateinit var viewModel: DishViewModel
    private var _binding: FragmentDishBinding? = null
    private var yallaActivity: MainActivity? = null
    private val binding: FragmentDishBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[DishViewModel::class.java]
        _binding = FragmentDishBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yallaActivity = (requireActivity() as MainActivity)
        yallaActivity!!.hideBnv()
        yallaActivity!!.showArrowBack()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.setDish(arguments?.getParcelable(CHOSEN_DISH, Dish::class.java)!!)
        } else {
            @Suppress("DEPRECATION")
            viewModel.setDish(arguments?.getParcelable(CHOSEN_RESTAURANT)!!)
        }
        viewModel.setRestaurantForRv((requireActivity() as MainActivity).getChosenDestinationId)
        //init views:
        with(binding) {
            with(viewModel.chosenDish) {
                Picasso.get().load(imageUrl).into(ivDish)
                tvNameOfDish.text = dishName
                tvDishPrice.text = getString(R.string.price_in_nis, price)
                tvDishDescription.text = description
                tvKosherTag.text = kosherTag
                tvKosherClass.text = groupTag
                rvAdditions.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
                tvAddToCart.text =
                    getString(R.string.add_to_cart, price)
                tvQuantity.text = "1"
                //Additions RV activation:
                viewModel.getAdditionsForRvByDishId(dishId)
                    .observe(viewLifecycleOwner) { additionsForRvList ->
                        rvAdditions.adapter = AdditionAdapter(
                            additionsForRvList,
                            handleAdditionClicked()
                        )
                    }
                //sum and show total price for dish+additions:
                //quantityLive:
                viewModel.dishCountLive.observe(viewLifecycleOwner) { dishCount ->
                    tvQuantity.text = dishCount.toString()
                    viewModel.chosenAdditionsLive.observe(viewLifecycleOwner) { chosenAdditions ->
                        val additionsPriceSum =
                            (chosenAdditions.stream().map { a -> a.totalPrice }.toList()
                                .sum())
                        tvAddToCart.text =
                            getString(
                                R.string.add_to_cart,
                                (price + additionsPriceSum) * dishCount
                            )
                    }
                }
                tvAddDish.setOnClickListener {
                    viewModel.addDishToCounter()
                }
                tvRemoveDish.setOnClickListener {
                    viewModel.removeDishFromCounter()
                }
                tvAddToCart.setOnClickListener {

                    val dishNotes = tilDishNotes.editText!!.text.toString()
                    val dinerName = tilDinerName.editText!!.text.toString()
                    viewModel.addOrderItemToCart(dishNotes, dinerName)
                    val bundle = Bundle()
                    viewModel.chosenRestLive.observe(viewLifecycleOwner) { chosenRest ->
                        bundle.putParcelable(CHOSEN_RESTAURANT, chosenRest)
                        findNavController().navigate(
                            R.id.action_dishFragment_to_restaurantMenu,
                            bundle
                        )
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.successfully_added_to_cart),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleAdditionClicked(): (AdditionForRv, Boolean) -> Unit =
        { addition, isClicked ->
            if (isClicked) {
                viewModel.addAddition(addition)
            } else {
                viewModel.removeAddition(addition)
            }
        }

    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).showTopBar()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).hideTopBar()
    }
}

