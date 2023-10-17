package com.example.walmartexercise.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartexercise.databinding.DisplayCountryBinding
import com.example.walmartexercise.model.data.CountryResponse

class CountryAdapter(
    private val dataSet: MutableList<CountryResponse> = mutableListOf()
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){

    fun setCountryList(newList: List<CountryResponse>) {
        dataSet.addAll(newList)
    }

    inner class CountryViewHolder(private val binding: DisplayCountryBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataItem: CountryResponse) {
            binding.apply {
                tvCapital.text = dataItem.capital
                tvName.text = buildString {
                    append(dataItem.name)
                    append(", ")
                 }
                tvRegion.text = dataItem.region
                tvCode.text = dataItem.code
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            DisplayCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}