package com.kasianov.sergei.omaloma.ui.absences.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.data.entities.Absence
import com.kasianov.sergei.omaloma.databinding.LayoutAbsenceItemBinding
import com.kasianov.sergei.omaloma.ui.users.adapter.AbsencesListAdapter

class AbsenceItemViewHolder(
    private val binding: LayoutAbsenceItemBinding,
    interaction: AbsencesListAdapter.Interaction?
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            interaction?.itemClicked(adapterPosition)
        }
    }

    fun bind(absence: Absence) {
        // TODO: Bind the data with View
        binding.tvAbsenceTitle.text = absence.title
    }
}