package com.strands.covid.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strands.covid.R
import com.strands.covid.databinding.ItemViewCountryBinding
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.generator.CountryFlagGenerator
import com.strands.covid.util.emptyInt
import com.strands.covid.util.loadImageUrl

class MainAdapter(private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var countryList = mutableListOf<AffectedCountry>()
        set(value) {
            field = value
            notifyDataChanged()
        }

    private var onClickListener: (AffectedCountry) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_country, parent, false)
        return ViewHolder(ItemViewCountryBinding.bind(view))
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    fun onClickListener(callBack: (AffectedCountry) -> Unit) {
        onClickListener = callBack
    }

    private fun notifyDataChanged() {
        notifyItemRangeChanged(emptyInt(), itemCount)
    }

    inner class ViewHolder(private val binding: ItemViewCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AffectedCountry) {
            val imagePath = CountryFlagGenerator.getFlagImagePath(data.countryCode)
            binding.tvCountryName.text = data.countryName
            binding.tvActiveCase.text = data.getActiveCase()
            binding.tvDeathCase.text = data.getDeathCase()
            binding.ivCountryFlag.loadImageUrl(context, imagePath)

            itemView.setOnClickListener {
                onClickListener.invoke(data)
            }
        }
    }
}
