package com.example.travelapp.ui.cities.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.data.entity.city.CityEntity
import com.example.travelapp.databinding.CardCityViewholderBinding

class CitiesAdapter(private val clickListener: CityClickListener): ListAdapter<CityEntity, CitiesAdapter.ViewHolder>(ChangeDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: CardCityViewholderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: CityEntity, clickListener: CityClickListener){
            binding.city = item;
            binding.cityClickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardCityViewholderBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ChangeDiffCallback : DiffUtil.ItemCallback<CityEntity>(){
        override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem.cityId == newItem.cityId
        }

        override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem == newItem
        }
    }

    class CityClickListener(val clickListener: (city: CityEntity) -> Unit){
        fun onClick(city: CityEntity) = clickListener(city)
    }


}