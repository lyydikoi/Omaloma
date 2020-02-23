package com.kasianov.sergei.omaloma.presentation.publicholidays.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.databinding.LayoutPublicHolidayItemBinding
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

class PublicHolidaysListAdapter(
    private val interaction: (Int) -> Unit
) : ListAdapter<PublicHoliday, PublicHolidaysListAdapter.PublicHolidaysViewHolder>(PublicHolidaysDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PublicHolidaysViewHolder(LayoutPublicHolidayItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        interaction
    )

    override fun onBindViewHolder(holder: PublicHolidaysViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<PublicHoliday>) {
        submitList(data.toMutableList())
    }

    class PublicHolidaysViewHolder(
        private val binding: LayoutPublicHolidayItemBinding,
        private val interaction: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PublicHoliday) {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                interaction(adapterPosition)
            }

            binding.tvDate.text = item.dateFormatted
            binding.tvTitle.text = item.localName
        }
    }

    private class PublicHolidaysDiffUtils : DiffUtil.ItemCallback<PublicHoliday>() {
        override fun areItemsTheSame(
            oldItem: PublicHoliday,
            newItem: PublicHoliday
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PublicHoliday,
            newItem: PublicHoliday
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.localName == newItem.localName
                    && oldItem.dateFormatted == newItem.dateFormatted
                    && oldItem.countryCode == newItem.countryCode
        }
    }

}