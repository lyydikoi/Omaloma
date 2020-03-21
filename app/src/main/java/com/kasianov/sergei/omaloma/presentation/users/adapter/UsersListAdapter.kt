package com.kasianov.sergei.omaloma.presentation.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.databinding.LayoutUserItemBinding

class UsersListAdapter(
    private val interaction: (Int) -> Unit
) : ListAdapter<UserDTO, UserItemViewHolder>(UserDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       UserItemViewHolder(
            LayoutUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
       )

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    private class UserDC : DiffUtil.ItemCallback<UserDTO>() {
        override fun areItemsTheSame(
            oldItem: UserDTO,
            newItem: UserDTO
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserDTO,
            newItem: UserDTO
        ): Boolean {
            return oldItem == newItem
        }
    }
}