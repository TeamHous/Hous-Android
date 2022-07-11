package com.hous.hous_aos.ui.rules.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hous.hous_aos.data.entity.rules.CategoryOfRuleResponse
import com.hous.hous_aos.databinding.ItemRulesRuleBinding

class CategoryOfRuleAdapter(private val onCategoryClick: () -> Unit) :
    ListAdapter<CategoryOfRuleResponse, CategoryOfRuleAdapter.CategoryOfRuleViewHolder>(
        CategoryOfRuleDiffUtilCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryOfRuleViewHolder {
        return CategoryOfRuleViewHolder(
            binding = ItemRulesRuleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCategoryClick = onCategoryClick
        )
    }

    override fun onBindViewHolder(holder: CategoryOfRuleViewHolder, position: Int) {
        val book = currentList[position]
        holder.onBind(book)
    }

    class CategoryOfRuleViewHolder(
        private val onCategoryClick: () -> Unit,
        private val binding: ItemRulesRuleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: CategoryOfRuleResponse) {
            binding.data = data
            Glide.with(binding.ivBear.context)
                .load(data.icon)
                .into(binding.ivBear)
            Glide.with(binding.ivRulesBackground.context)
                .load(data.backGround)
                .into(binding.ivRulesBackground)

            binding.root.setOnClickListener {
                onCategoryClick
            }
        }
    }

    companion object {
        private val CategoryOfRuleDiffUtilCallback =
            object : DiffUtil.ItemCallback<CategoryOfRuleResponse>() {
                override fun areItemsTheSame(
                    oldItem: CategoryOfRuleResponse,
                    newItem: CategoryOfRuleResponse
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: CategoryOfRuleResponse,
                    newItem: CategoryOfRuleResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
