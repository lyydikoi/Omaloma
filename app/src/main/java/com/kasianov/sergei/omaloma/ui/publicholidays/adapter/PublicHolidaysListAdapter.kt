package com.kasianov.sergei.omaloma.ui.publicholidays.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.data.source.remote.dtos.PublicHolidayDto
import com.kasianov.sergei.omaloma.databinding.LayoutPublicHolidayItemBinding

class PublicHolidaysListAdapter(
    private val interaction: (Int) -> Unit
) : ListAdapter<PublicHolidayDto, PublicHolidaysListAdapter.PublicHolidaysViewHolder>(PublicHolidaysDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PublicHolidaysViewHolder(
            LayoutPublicHolidayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction)

    override fun onBindViewHolder(holder: PublicHolidaysViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<PublicHolidayDto>) {
        submitList(data.toMutableList())
    }

    class PublicHolidaysViewHolder(
        private val binding: LayoutPublicHolidayItemBinding,
        private val interaction: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PublicHolidayDto) {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                interaction(adapterPosition)
            }
            binding.tvDate.text = item.date
            binding.tvTitle.text = item.localName
        }
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