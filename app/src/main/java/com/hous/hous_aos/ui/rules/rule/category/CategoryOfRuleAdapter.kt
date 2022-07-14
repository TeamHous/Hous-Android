package com.hous.hous_aos.ui.rules.rule.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.rules.CategoryOfRuleResponse
import com.hous.hous_aos.databinding.ItemRulesRuleBinding
import com.hous.hous_aos.ui.rules.rule.ItemCategoryViewType

class CategoryOfRuleAdapter(
    private val onCategoryClick: () -> Unit,
    private val onPlusClick: () -> Unit,
    private val onChangeIsSelected: (Int) -> Unit,
) :
    ListAdapter<CategoryOfRuleResponse, RecyclerView.ViewHolder>(
        CategoryOfRuleDiffUtilCallback
    ) {

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size - 1) {
            ItemCategoryViewType.PLUS_VIEW_TYPE.index
        } else {
            ItemCategoryViewType.GENERAL_VIEW_TYPE.index
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemCategoryViewType.PLUS_VIEW_TYPE.index ->
                PlusViewHolder(
                    onPlusClick,
                    ItemRulesRuleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> CategoryOfRuleViewHolder(
                onCategoryClick,
                onChangeIsSelected,
                ItemRulesRuleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (holder) {
            is CategoryOfRuleViewHolder -> holder.onBind(data)
            is PlusViewHolder -> holder.onBind(data)
        }
    }

    class CategoryOfRuleViewHolder(
        private val onCategoryClick: () -> Unit,
        private val onChangeIsSelected: (Int) -> Unit,
        private val binding: ItemRulesRuleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: CategoryOfRuleResponse) {
            binding.data = data
            binding.clRuleItem.setOnClickListener {
                onCategoryClick()
                onChangeIsSelected(adapterPosition)
            }
        }
    }

    class PlusViewHolder(
        private val onPlusClick: () -> Unit,
        private val binding: ItemRulesRuleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: CategoryOfRuleResponse) {
            binding.data = data
            binding.ivRuleIcon.visibility = View.INVISIBLE
            binding.clRuleItem.setOnClickListener {
                onPlusClick()
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
