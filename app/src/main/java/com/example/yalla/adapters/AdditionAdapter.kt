package com.example.yalla.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.R
import com.example.yalla.databinding.AdditionItemBinding
import com.example.yalla.models.AdditionForRv

class AdditionAdapter(
    private val additionsList: List<AdditionForRv>,
    private val onAdditionClicked: (AdditionForRv, Boolean) -> Unit
) :
    RecyclerView.Adapter<AdditionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdditionItemBinding.inflate(inflater, parent, false)
        return AdditionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdditionViewHolder, position: Int) {
        val addition = additionsList[position]
        with(holder.binding.root) {
            if (addition.totalPrice == 0.0) {
                text = addition.additionName
                textOn = addition.additionName
                textOff = addition.additionName
            } else {
                val textWithPrice =
                    context.getString(
                        R.string.addition_name_with_price,
                        addition.additionName,
                        addition.totalPrice
                    )
                text = textWithPrice
                textOn = textWithPrice
                textOff = textWithPrice
            }

            if (addition.available) {
                val textSelectedColor = ResourcesCompat.getColor(this.resources, R.color.pink, null)
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        setTextColor(textSelectedColor)
                    } else {
                        setTextColor(Color.WHITE)
                    }
                    onAdditionClicked.invoke(addition, isChecked)
                }
            } else {
                isClickable = false
                background = AppCompatResources.getDrawable(context, R.drawable.form_style)
                text = context.getString(R.string.addition_out_of_stock, addition.additionName)
                setTextColor(ResourcesCompat.getColor(this.resources, R.color.black, null))
            }
        }
    }

    override fun getItemCount(): Int =
        additionsList.size


}

class AdditionViewHolder(val binding: AdditionItemBinding) :
    RecyclerView.ViewHolder(binding.root)