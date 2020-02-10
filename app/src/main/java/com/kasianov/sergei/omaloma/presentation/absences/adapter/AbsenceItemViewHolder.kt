package com.kasianov.sergei.omaloma.presentation.absences.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.data.absence.local.DBAbsence
import com.kasianov.sergei.omaloma.databinding.LayoutAbsenceItemBinding
import com.kasianov.sergei.omaloma.presentation.users.adapter.AbsencesListAdapter

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

    fun bind(absence: DBAbsence) {
        // TODO: Bind the data with View
        binding.tvAbsenceTitle.text = absence.title
    }
}