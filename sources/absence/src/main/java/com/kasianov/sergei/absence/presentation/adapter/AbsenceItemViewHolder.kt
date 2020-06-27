package com.kasianov.sergei.absence.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.absence.databinding.LayoutAbsenceItemBinding
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO

class AbsenceItemViewHolder(
    private val binding: LayoutAbsenceItemBinding,
    interaction: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            interaction(adapterPosition)
        }
    }

    fun bind(absence: AbsenceDTO) {
        // TODO: Bind the data with View
        binding.tvAbsenceTitle.text = absence.title
    }
}
