package com.hous.hous_aos.ui.rules.rules_table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.domain.model.RuleInfo
import com.hous.hous_aos.databinding.ItemRulesTableKeysBinding

class KeyRulesAdapter :
    ListAdapter<RuleInfo, KeyRulesAdapter.KeyRulesViewHolder>(rulesTableDiffUtil) {

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
        fun onBind(data: RuleInfo) {
            binding.data = data
        }
    }

    companion object {
        private val rulesTableDiffUtil = object : DiffUtil.ItemCallback<RuleInfo>() {
            override fun areItemsTheSame(oldItem: RuleInfo, newItem: RuleInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RuleInfo, newItem: RuleInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
