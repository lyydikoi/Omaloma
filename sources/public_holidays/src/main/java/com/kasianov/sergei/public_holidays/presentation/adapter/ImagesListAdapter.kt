package com.kasianov.sergei.public_holidays.presentation.adapter

import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kasianov.sergei.public_holidays.R
import com.kasianov.sergei.public_holidays.databinding.LayoutSwipeImageItemBinding


class ImagesListAdapter(
    private val interaction:(Int) -> Unit
) : ListAdapter<String, ImagesListAdapter.ImageSwipeCardViewHolder>(ImageDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ImageSwipeCardViewHolder =
        ImageSwipeCardViewHolder(
            LayoutSwipeImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )

    fun swapData(data: List<String>) {
        submitList(data)
    }

    override fun onBindViewHolder(holder: ImageSwipeCardViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ImageSwipeCardViewHolder(
        private val binding: LayoutSwipeImageItemBinding,
        private val interaction:(Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String) {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                interaction(adapterPosition)
            }
            Glide.with(binding.ivWikiImage.context)
                .load(url)
                .placeholder(R.drawable.ic_image_light_green_48dp)
                .into(binding.ivWikiImage)
        }
    }

    private class ImageDiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }
}