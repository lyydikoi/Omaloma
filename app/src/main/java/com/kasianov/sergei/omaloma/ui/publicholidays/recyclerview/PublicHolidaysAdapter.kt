package com.kasianov.sergei.omaloma.ui.publicholidays.recyclerview

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.data.source.remote.responsemodels.PublicHolidayResponse
import com.kasianov.sergei.omaloma.databinding.LayoutPublicHolidayItemBinding

class PublicHolidaysAdapter(private val interaction: Interaction? = null) :
    ListAdapter<PublicHolidayResponse, PublicHolidaysAdapter.PublicHolidaysViewHolder>(
        PublicHolidaysResponseDC()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PublicHolidaysViewHolder(
        LayoutPublicHolidayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        interaction
    )

    override fun onBindViewHolder(holder: PublicHolidaysViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<PublicHolidayResponse>) {
        submitList(data.toMutableList())
    }

    inner class PublicHolidaysViewHolder(
        private val binding: LayoutPublicHolidayItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                interaction?.itemClicked(adapterPosition)
            }
        }

        fun bind(item: PublicHolidayResponse) {
            binding.tvDate.text = item.date
            binding.tvTitle.text = item.localName
        }
    }

    interface Interaction {
        fun itemClicked(position: Int)
    }

    private class PublicHolidaysResponseDC : DiffUtil.ItemCallback<PublicHolidayResponse>() {
        override fun areItemsTheSame(
            oldItem: PublicHolidayResponse,
            newItem: PublicHolidayResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PublicHolidayResponse,
            newItem: PublicHolidayResponse
        ): Boolean {
            return oldItem.type == newItem.type
                    && oldItem.name == newItem.name
                    && oldItem.localName == newItem.localName
                    && oldItem.countryCode == newItem.countryCode
                    && oldItem.launchYear == newItem.launchYear
        }
    }
}