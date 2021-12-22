package com.example.retrofitforecaster
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ViewHolderHot(view: View) : RecyclerView.ViewHolder(view) { // Для тёплой
    private val datetime: TextView = view.findViewById(R.id.datetime_text)
    private val temperature: TextView = view.findViewById(R.id.temperature_text)

    fun bindTo(weather: ListItem) {
        temperature.text = weather.main.temp.toString()
        datetime.text = weather.dt_txt
    }
}

class ViewHolderCold(view: View) : RecyclerView.ViewHolder(view) { // Для холодной
        private val datetime: TextView = view.findViewById(R.id.datetime_text)
        private val temperature: TextView = view.findViewById(R.id.temperature_text)

        fun bindTo(weather: ListItem, position: Int){
            temperature.text = weather.main.temp.toString()
            datetime.text = weather.dt_txt
        }
}

class Adapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(ContactItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
            view.setBackgroundColor(Color.rgb(208, 65, 65))
            ViewHolderHot(view)
        }
        else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item2, parent, false)
            view.setBackgroundColor(Color.rgb(26, 103, 212))
            ViewHolderCold(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        if (data.main.temp > 0) { // Если температура больше нуля
            val hot: ViewHolderHot = holder as ViewHolderHot
            hot.bindTo(data)
        }
        else {
            val cold: ViewHolderCold = holder as ViewHolderCold
            cold.bindTo(data, position)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].main.temp > 0) {
            0
        }
        else {
            1
        }
    }
}

class ContactItemDiffCallback : DiffUtil.ItemCallback<ListItem>(){
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
}

