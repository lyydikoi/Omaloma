package com.kasianov.sergei.absence.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.absence.databinding.LayoutAbsenceItemBinding
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO

class AbsencesListAdapter(
    private val interaction: (Int) -> Unit
) : ListAdapter<AbsenceDTO, AbsenceItemViewHolder>(
    UserDC()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AbsenceItemViewHolder(
            LayoutAbsenceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )

    override fun onBindViewHolder(holder: AbsenceItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    private class UserDC : DiffUtil.ItemCallback<AbsenceDTO>() {
        override fun areItemsTheSame(
            oldItem: AbsenceDTO,
            newItem: AbsenceDTO
        ): Boolean {
            return oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(
            oldItem: AbsenceDTO,
            newItem: AbsenceDTO
        ): Boolean {
            return oldItem == newItem
        }
    }
}