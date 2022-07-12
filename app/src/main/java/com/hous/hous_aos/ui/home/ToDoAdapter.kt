package com.hous.hous_aos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeToDoBinding

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    val toDoList = mutableListOf<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            ItemHomeToDoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.onBind(toDoList[position])
    }

    override fun getItemCount(): Int = 5

    class ToDoViewHolder(
        private val binding: ItemHomeToDoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ToDoData) {
            binding.toDoData = data
        }
    }
}