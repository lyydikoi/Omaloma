package com.kasianov.sergei.omaloma.ui.publicholidays.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kasianov.sergei.omaloma.R
import kotlinx.android.synthetic.main.image_swipe_item_layout.view.*

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates


class ImageAdapter
    : RecyclerView.Adapter<ImageAdapter.ImageSwipeCardViewHolder>() {

    var items: List<String> by Delegates.observable(emptyList()) { _, oldList, newList ->
        notifyChanges(oldList, newList)
    }

    private fun notifyChanges(oldList: List<String>, newList: List<String>) {
        val diff = DiffUtil.calculateDiff(ImageDiffUtilCallback(oldList, newList))
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ImageSwipeCardViewHolder {
        return ImageSwipeCardViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.image_swipe_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageSwipeCardViewHolder, position: Int) {
        val url = items[position]
        Glide.with(holder.itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_image_light_green_48dp)
            .into(holder.iv)
    }

    inner class ImageSwipeCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.iv_wiki_image
    }

    private class ImageDiffUtilCallback(
        private val oldList: List<String>, private val newList: List<String>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}