package com.kasianov.sergei.omaloma.ui.publicholidays.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.data.source.remote.dtos.PublicHolidayDto
import com.kasianov.sergei.omaloma.databinding.LayoutPublicHolidayItemBinding

class PublicHolidaysAdapter(private val interaction: Interaction? = null) :
    ListAdapter<PublicHolidayDto, PublicHolidaysAdapter.PublicHolidaysViewHolder>(
        PublicHolidaysDiffUtils()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PublicHolidaysViewHolder(
        LayoutPublicHolidayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        interaction
    )

    override fun onBindViewHolder(holder: PublicHolidaysViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<PublicHolidayDto>) {
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

        fun bind(item: PublicHolidayDto) {
            binding.tvDate.text = item.date
            binding.tvTitle.text = item.localName
        }
    }

    interface Interaction {
        fun itemClicked(position: Int)
    }

    private class PublicHolidaysDiffUtils : DiffUtil.ItemCallback<PublicHolidayDto>() {
        override fun areItemsTheSame(
            oldItem: PublicHolidayDto,
            newItem: PublicHolidayDto
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PublicHolidayDto,
            newItem: PublicHolidayDto
        ): Boolean {
            return oldItem.type == newItem.type
                    && oldItem.name == newItem.name
                    && oldItem.localName == newItem.localName
                    && oldItem.countryCode == newItem.countryCode
                    && oldItem.launchYear == newItem.launchYear
        }
    }
}