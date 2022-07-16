package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeComingUpBinding
import com.hous.hous_aos.ui.home.ComingUpData

class ComingUpAdapter :
    ListAdapter<ComingUpData, ComingUpAdapter.ComingUpViewHolder>(comingUpDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingUpViewHolder {
        val binding =
            ItemHomeComingUpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComingUpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComingUpViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ComingUpViewHolder(val binding: ItemHomeComingUpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ComingUpData) {
            binding.ivComingUp.setImageResource(data.image)
            binding.comingUpData = data
            binding.position = adapterPosition
        }
    }

    companion object {
        private val comingUpDiffUtil = object : DiffUtil.ItemCallback<ComingUpData>() {
            override fun areItemsTheSame(oldItem: ComingUpData, newItem: ComingUpData): Boolean =
                oldItem.day == newItem.day

            override fun areContentsTheSame(oldItem: ComingUpData, newItem: ComingUpData): Boolean =
                oldItem == newItem
        }
    }
}
