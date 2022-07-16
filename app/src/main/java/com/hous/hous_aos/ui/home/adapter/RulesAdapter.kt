package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeRulesBinding
import com.hous.hous_aos.ui.home.RulesData

class RulesAdapter : ListAdapter<RulesData, RulesAdapter.RulesViewHolder>(rulesDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val binding =
            ItemHomeRulesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RulesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RulesViewHolder(val binding: ItemHomeRulesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RulesData) {
            binding.rulesData = data
        }
    }

    companion object {
        private val rulesDiffUtil = object : DiffUtil.ItemCallback<RulesData>() {
            override fun areItemsTheSame(oldItem: RulesData, newItem: RulesData): Boolean =
                oldItem.rules == newItem.rules

            override fun areContentsTheSame(oldItem: RulesData, newItem: RulesData): Boolean =
                oldItem == newItem
        }
    }
}
