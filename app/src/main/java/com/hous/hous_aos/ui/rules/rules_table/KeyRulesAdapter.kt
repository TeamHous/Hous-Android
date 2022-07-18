package com.hous.hous_aos.ui.rules.rules_table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.rules.KeyRulesData
import com.hous.hous_aos.databinding.ItemRulesTableKeysBinding

class KeyRulesAdapter :
    ListAdapter<KeyRulesData, KeyRulesAdapter.KeyRulesViewHolder>(rulesTableDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyRulesViewHolder {
        val binding =
            ItemRulesTableKeysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeyRulesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeyRulesViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.onBind(currentItem)
    }

    class KeyRulesViewHolder(private val binding: ItemRulesTableKeysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: KeyRulesData) {
            binding.data = data
        }
    }

    companion object {
        private val rulesTableDiffUtil = object : DiffUtil.ItemCallback<KeyRulesData>() {
            override fun areItemsTheSame(oldItem: KeyRulesData, newItem: KeyRulesData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: KeyRulesData, newItem: KeyRulesData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
