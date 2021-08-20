package com.example.travelapp.ui.overview.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.data.entity.country.CountryEntity
import com.example.travelapp.databinding.CardCountryViewholderBinding

class CountriesAdapter(private val clickListener: ClickListener): ListAdapter<CountryEntity, CountriesAdapter.ViewHolder>(ChangeDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: CardCountryViewholderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: CountryEntity, clicklistener: ClickListener){
            binding.country = item;
            binding.clickListener= clicklistener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent:ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardCountryViewholderBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ChangeDiffCallback : DiffUtil.ItemCallback<CountryEntity>(){
        override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem.countryId == newItem.countryId
        }

        override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem == newItem
        }
    }

    class ClickListener(val clickListener: (country: CountryEntity) -> Unit){
        fun onClick(country: CountryEntity) = clickListener(country)
    }


}