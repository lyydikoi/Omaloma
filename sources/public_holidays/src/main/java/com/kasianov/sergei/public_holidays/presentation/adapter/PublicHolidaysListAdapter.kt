package com.kasianov.sergei.public_holidays.presentation.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.public_holidays.databinding.LayoutPublicHolidayItemBinding
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO

class PublicHolidaysListAdapter(
    private val interaction: (Int) -> Unit
) : ListAdapter<PublicHolidayDTO, PublicHolidaysListAdapter.PublicHolidaysViewHolder>(PublicHolidaysDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PublicHolidaysViewHolder(
            LayoutPublicHolidayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )

    override fun onBindViewHolder(holder: PublicHolidaysViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<PublicHolidayDTO>) {
        submitList(data.toMutableList())
    }

    class PublicHolidaysViewHolder(
        private val binding: LayoutPublicHolidayItemBinding,
        private val interaction: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PublicHolidayDTO) {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                interaction(adapterPosition)
            }

            binding.tvDate.text = item.date
            binding.tvTitle.text = item.localName
        }
    }

    private class PublicHolidaysDiffUtils : DiffUtil.ItemCallback<PublicHolidayDTO>() {
        override fun areItemsTheSame(
            oldItem: PublicHolidayDTO,
            newItem: PublicHolidayDTO
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PublicHolidayDTO,
            newItem: PublicHolidayDTO
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.localName == newItem.localName
                    && oldItem.date == newItem.date
                    && oldItem.countryCode == newItem.countryCode
        }
    }

}