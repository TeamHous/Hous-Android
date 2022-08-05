package com.hous.hous_aos.ui.rules.rules_table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.data.entity.Rule
import com.hous.hous_aos.databinding.ItemRulesTableKeysBinding

class KeyRulesAdapter :
    ListAdapter<com.hous.data.entity.Rule, KeyRulesAdapter.KeyRulesViewHolder>(rulesTableDiffUtil) {

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
        fun onBind(data: com.hous.data.entity.Rule) {
            binding.data = data
        }
    }

    companion object {
        private val rulesTableDiffUtil = object : DiffUtil.ItemCallback<com.hous.data.entity.Rule>() {
            override fun areItemsTheSame(oldItem: com.hous.data.entity.Rule, newItem: com.hous.data.entity.Rule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: com.hous.data.entity.Rule, newItem: com.hous.data.entity.Rule): Boolean {
                return oldItem == newItem
            }
        }
    }
}
