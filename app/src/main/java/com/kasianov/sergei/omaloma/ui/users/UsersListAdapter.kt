package com.kasianov.sergei.omaloma.ui.users

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kasianov.sergei.omaloma.R
import com.kasianov.sergei.omaloma.data.entities.User

class UsersListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<User, UsersListAdapter.UsersItemViewHolder>(UserDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_users_item_view_holder, parent, false), interaction
    )

    override fun onBindViewHolder(holder: UsersItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<User>) {
        submitList(data.toMutableList())
    }

    inner class UsersItemViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
        }

        fun bind(item: User) = with(itemView) {
            // TODO: Bind the data with View
        }
    }

    interface Interaction {

    }

    private class UserDC : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            TODO(
                "not implemented"
            )
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            TODO(
                "not implemented"
            )
        }
    }
}