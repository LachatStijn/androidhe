package com.example.travelapp.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("hourForecast")
fun TextView.setHourForecast(item : String){
    item.let {
        var hour = item.subSequence(11, item.length-3)
        text = hour
    }
}

@BindingAdapter("dateForecast")
fun TextView.setDateForecast(item : String){
    item.let {
        var date = item.subSequence(0, 10)
        text = date
    }
}

@BindingAdapter("reformatTempDoublesDetails")
fun TextView.setDoublesDetails(item : Double){
    item.let {
        var newString = Math.round(item).toString()
        text = "Temperatuur: $newString°"
    }
}

@BindingAdapter("reformatTempDoubles")
fun TextView.setDoubles(item : Double){
    item.let {
        var newString = Math.round(item).toString()
        text = "$newString°"
    }
}

@BindingAdapter("tempPressure")
fun TextView.setPressure(item : Int){
    item.let {
        text = "Luchtdruk: ${item} mb"
    }
}


@BindingAdapter("tempHumidity")
fun TextView.setHumidity(item : Int){
    item.let {
        text = "Neerslagkans: ${item}%"
    }
}



@BindingAdapter("windSpeed")
fun TextView.setWindSpeed(item : Double){
    item.let {
        var inKm = Math.round(item * 3.6)
        text = "Windsnelheid: ${inKm} km/u"
    }
}

@BindingAdapter("visib")
fun TextView.setVisib(item : Int){
    item.let {
        var inKm = (item / 1000).toString()
        text = "Zicht: ${inKm} km"
    }
}

@BindingAdapter("description")
fun TextView.setBeschrijving(descr : String){
    descr.let {
        text = "Beschrijving: ${descr}"
    }
}