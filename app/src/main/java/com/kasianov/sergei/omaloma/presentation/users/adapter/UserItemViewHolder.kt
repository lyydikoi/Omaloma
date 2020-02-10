package com.kasianov.sergei.omaloma.presentation.users.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.omaloma.data.user.local.DBUser
import com.kasianov.sergei.omaloma.databinding.LayoutUserItemBinding

class UserItemViewHolder(
    private val binding: LayoutUserItemBinding,
    interaction: UsersListAdapter.Interaction?
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            interaction?.itemClicked(adapterPosition)
        }
    }

    fun bind(user: DBUser) {
        // TODO: Bind the data with View
        binding.tvFirstName.text = user.firstName
    }
}