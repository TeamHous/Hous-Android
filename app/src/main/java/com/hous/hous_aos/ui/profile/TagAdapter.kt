package com.hous.hous_aos.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemProfileTagBinding

class TagAdapter :
    ListAdapter<TagData, TagAdapter.TagViewHolder>(tagDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding =
            ItemProfileTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class TagViewHolder(val binding: ItemProfileTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagData) {
            binding.tagData = data
        }
    }

    companion object {
        private val tagDiffUtil = object : DiffUtil.ItemCallback<TagData>() {
            override fun areItemsTheSame(oldItem: TagData, newItem: TagData): Boolean =
                oldItem.alias == newItem.alias

            override fun areContentsTheSame(oldItem: TagData, newItem: TagData): Boolean =
                oldItem == newItem
        }
    }
}
