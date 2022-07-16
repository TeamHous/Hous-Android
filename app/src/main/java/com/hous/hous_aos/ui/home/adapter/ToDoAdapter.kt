package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeToDoBinding
import com.hous.hous_aos.ui.home.ToDoData

class ToDoAdapter : ListAdapter<ToDoData, ToDoAdapter.ToDoViewHolder>(toDoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            ItemHomeToDoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ToDoViewHolder(val binding: ItemHomeToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ToDoData) {
            binding.toDoData = data
        }
    }

    companion object {
        private val toDoDiffUtil = object : DiffUtil.ItemCallback<ToDoData>() {
            override fun areItemsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean =
                oldItem.rules == newItem.rules

            override fun areContentsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean =
                oldItem == newItem
        }
    }
}
