package com.hous.hous_aos.ui.rules

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.data.entity.Category
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ItemRulesRuleBinding

class HomeRulesCategoryAdapter(
    private val onLongClick: () -> Unit,
    private val onCategoryClick: () -> Unit,
    private val onPlusClick: () -> Unit,
    private val onChangeIsSelected: (Int) -> Unit
) :
    ListAdapter<Category, RecyclerView.ViewHolder>(
        CategoryOfRuleDiffUtilCallback
    ) {
    private val iconTypeHashMap: HashMap<String, CategoryIconType> = hashMapOf(
        "CLEAN" to CategoryIconType.CLEAN,
        "TRASH" to CategoryIconType.TRASH,
        "HEART" to CategoryIconType.HEART,
        "LIGHT" to CategoryIconType.LIGHT,
        "BEER" to CategoryIconType.BEER,
        "CAKE" to CategoryIconType.CAKE,
        "LAUNDRY" to CategoryIconType.LAUNDRY,
        "COFFEE" to CategoryIconType.COFFEE
    )

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
                iconTypeHashMap,
                onLongClick,
                onCategoryClick,
                onChangeIsSelected,
                ItemRulesRuleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
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
        private val iconTypeHashMap: HashMap<String, CategoryIconType>,
        private val onLongClick: () -> Unit,
        private val onCategoryClick: () -> Unit,
        private val onChangeIsSelected: (Int) -> Unit,
        private val binding: ItemRulesRuleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: com.hous.data.entity.Category) {
            binding.data = data
            binding.iconType = iconTypeHashMap[data.categoryIcon]
            /** 앱잼 내에서는 비활성화*/
//            binding.clRuleItem.setOnLongClickListener {
//                onLongClick()
//                onChangeIsSelected(absoluteAdapterPosition)
//                return@setOnLongClickListener true
//            }
            binding.clRuleItem.setOnClickListener {
                onCategoryClick()
                onChangeIsSelected(absoluteAdapterPosition)
            }
        }
    }

    class PlusViewHolder(
        private val onPlusClick: () -> Unit,
        private val binding: ItemRulesRuleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: com.hous.data.entity.Category) {
            binding.data = data
            binding.iconType = CategoryIconType.NONE
            /** 앱잼 내에서는 비활성화*/
//            binding.clRuleItem.setOnClickListener {
//                onPlusClick()
//            }
        }
    }

    companion object {
        private val CategoryOfRuleDiffUtilCallback =
            object : DiffUtil.ItemCallback<com.hous.data.entity.Category>() {
                override fun areItemsTheSame(
                    oldItem: com.hous.data.entity.Category,
                    newItem: com.hous.data.entity.Category
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: com.hous.data.entity.Category,
                    newItem: com.hous.data.entity.Category
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}

enum class CategoryIconType(@DrawableRes val background: Int, @DrawableRes val drawableRes: Int) {
    CLEAN(R.drawable.ic_rules_category_blue_bg_2, R.drawable.ic_rules_broom_s),
    TRASH(R.drawable.ic_rules_category_blue_bg_2, R.drawable.ic_rules_trash_s),
    LIGHT(R.drawable.ic_rules_category_red_bg_m, R.drawable.ic_rules_bulb_s),
    HEART(R.drawable.ic_rules_category_red_bg_m, R.drawable.ic_rules_heart_s),
    BEER(R.drawable.ic_rules_category_yellow_bg_m, R.drawable.ic_rules_beer_s),
    CAKE(R.drawable.ic_rules_category_yellow_bg_m, R.drawable.ic_rules_pancake_s),
    LAUNDRY(R.drawable.ic_rules_category_purple_bg_m, R.drawable.ic_rules_laundry_s),
    COFFEE(R.drawable.ic_rules_category_purple_bg_m, R.drawable.ic_rules_coffee_s),
    NONE(R.drawable.ic_rules_category_transparent_bg_m, R.drawable.ic_rules_todo_plus)
}
