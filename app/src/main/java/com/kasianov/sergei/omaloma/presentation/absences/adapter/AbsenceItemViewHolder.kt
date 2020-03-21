package com.kasianov.sergei.omaloma.presentation.absences.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.data.database.dto.DBAbsence
import com.kasianov.sergei.omaloma.databinding.LayoutAbsenceItemBinding

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

    fun bind(absence: DBAbsence) {
        // TODO: Bind the data with View
        binding.tvAbsenceTitle.text = absence.title
    }
}