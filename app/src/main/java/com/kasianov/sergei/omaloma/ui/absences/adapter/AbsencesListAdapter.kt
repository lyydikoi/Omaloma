package com.kasianov.sergei.omaloma.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.data.entities.Absence
import com.kasianov.sergei.omaloma.databinding.LayoutAbsenceItemBinding
import com.kasianov.sergei.omaloma.ui.absences.adapter.AbsenceItemViewHolder

class AbsencesListAdapter(
    private val interaction: Interaction? = null
) : ListAdapter<Absence, AbsenceItemViewHolder>(UserDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AbsenceItemViewHolder(
            LayoutAbsenceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )

    override fun onBindViewHolder(holder: AbsenceItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    interface Interaction {
        fun itemClicked(position: Int)
    }

    private class UserDC : DiffUtil.ItemCallback<Absence>() {
        override fun areItemsTheSame(
            oldItem: Absence,
            newItem: Absence
        ): Boolean {
            return oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(
            oldItem: Absence,
            newItem: Absence
        ): Boolean {
            return oldItem == newItem
        }
    }
}