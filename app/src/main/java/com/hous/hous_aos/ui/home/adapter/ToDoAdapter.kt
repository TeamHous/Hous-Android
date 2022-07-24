package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.databinding.ItemHomeToDoBinding

class ToDoAdapter : ListAdapter<Rule, ToDoAdapter.ToDoViewHolder>(toDoDiffUtil) {

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
        fun onBind(data: Rule) {
            binding.cbToDo.isChecked = data.isChecked != false
            binding.todo = data
        }
    }

    companion object {
        private val toDoDiffUtil = object : DiffUtil.ItemCallback<Rule>() {
            override fun areItemsTheSame(oldItem: Rule, newItem: Rule): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Rule, newItem: Rule): Boolean =
                oldItem == newItem
        }
    }
}
