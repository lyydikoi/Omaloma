package com.kasianov.sergei.omaloma.presentation.users.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.databinding.LayoutUserItemBinding

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