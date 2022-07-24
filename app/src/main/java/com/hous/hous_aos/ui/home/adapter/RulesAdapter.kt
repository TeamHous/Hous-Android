package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeRulesBinding

class RulesAdapter : RecyclerView.Adapter<RulesAdapter.RulesViewHolder>() {
    val rulesList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val binding =
            ItemHomeRulesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return RulesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.onBind(rulesList[position])
    }

    override fun getItemCount(): Int = rulesList.size

    class RulesViewHolder(
        private val binding: ItemHomeRulesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            binding.rules = data
        }
    }
}