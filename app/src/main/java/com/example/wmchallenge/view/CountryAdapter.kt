package com.example.wmchallenge.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wmchallenge.R

import com.example.wmchallenge.model.data.Country

class CountryAdapter(private val items: List<Country>, private val context: Context): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameRegion: TextView = itemView.findViewById(R.id.nameRegion)
        val code: TextView = itemView.findViewById(R.id.code)
        val capital: TextView = itemView.findViewById(R.id.capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.nameRegion.text =
            context.getString(R.string.name_region, items[position].name, items[position].name)
        holder.code.text = items[position].code
        holder.capital.text = items[position].capital
    }
}