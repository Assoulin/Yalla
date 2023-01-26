package com.example.yalla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yalla.databinding.DestinationItemBinding
import com.example.yalla.models.Destination


class DestinationAdapter(
    private val destinations: List<Destination>,
    private val onDestinationClicked: (Destination)-> Unit,
    ) :
    RecyclerView.Adapter<DestinationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DestinationItemBinding.inflate(inflater, parent, false)
        return DestinationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]

        with(holder.binding) {
            btnDestination.text = destination.destinationName
            root.setOnClickListener {
                onDestinationClicked.invoke(destination)
            }
        }

    }

    override fun getItemCount(): Int = destinations.size
}

class DestinationViewHolder(val binding: DestinationItemBinding) :
    RecyclerView.ViewHolder(binding.root)