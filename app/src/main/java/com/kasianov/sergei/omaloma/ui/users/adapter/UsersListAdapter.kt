package com.kasianov.sergei.omaloma.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.databinding.LayoutUserItemBinding

class UsersListAdapter(
    private val interaction: Interaction? = null
) : ListAdapter<User, UserItemViewHolder>(UserDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       UserItemViewHolder(
            LayoutUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
       )

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    interface Interaction {
        fun itemClicked(position: Int)
    }

    private class UserDC : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }
    }
}