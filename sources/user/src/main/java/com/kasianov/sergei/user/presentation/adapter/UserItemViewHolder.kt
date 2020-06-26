package com.kasianov.sergei.user.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.core_api.model.dto.UserDTO
import com.kasianov.sergei.user.databinding.LayoutUserItemBinding

class UserItemViewHolder(
    private val binding: LayoutUserItemBinding,
    interaction: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            interaction(adapterPosition)
        }
    }

    fun bind(user: UserDTO) {
        // TODO: Bind the data with View
        binding.tvFirstName.text = user.firstName
    }
}
