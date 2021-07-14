package com.example.travelapp.ui.weather.main.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.data.model.WeatherForecast
import com.example.travelapp.databinding.CardWeatherViewholderBinding

class WeatherAdapter(private val clickListener: WeatherClickListener): ListAdapter<WeatherForecast, WeatherAdapter.ViewHolder>(ChangeDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: CardWeatherViewholderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: WeatherForecast, clickListener: WeatherClickListener){
            binding.weatherForecast = item;
            binding.clickListener= clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent:ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardWeatherViewholderBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ChangeDiffCallback : DiffUtil.ItemCallback<WeatherForecast>(){
        override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
            return oldItem == newItem
        }
    }

    class WeatherClickListener(val clickListener: (wf:WeatherForecast) -> Unit){
        fun onClick(wf: WeatherForecast) = clickListener(wf)
    }


}