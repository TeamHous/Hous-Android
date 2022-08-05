package com.hous.hous_aos.ui.rules.my_to_do

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.data.entity.Rule
import com.hous.hous_aos.databinding.ItemRulesMyToDoBinding

class MyToDoAdapter(private val onClickIcon: (Int) -> Unit) :
    ListAdapter<com.hous.data.entity.Rule, MyToDoAdapter.MyToDoViewHolder>(
        MyTodoDiffUtilCallback
    ) {
    private val iconTypeHashMap: HashMap<String, IconType> = hashMapOf(
        "CLEAN" to IconType.CLEAN,
        "TRASH" to IconType.TRASH,
        "HEART" to IconType.HEART,
        "LIGHT" to IconType.LIGHT,
        "BEER" to IconType.BEER,
        "CAKE" to IconType.CAKE,
        "LAUNDRY" to IconType.LAUNDRY,
        "COFFEE" to IconType.COFFEE
    )

    class MyToDoViewHolder(
        private val binding: ItemRulesMyToDoBinding,
        private val iconTypeHashMap: HashMap<String, IconType>,
        private val onClickIcon: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: com.hous.data.entity.Rule) {
            binding.data = data
            binding.iconType = iconTypeHashMap[data.categoryIcon]
            binding.clMyToDo.setOnClickListener {
                binding.ivCheckBox.isSelected = !binding.ivCheckBox.isSelected
                onClickIcon(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyToDoViewHolder {

        val binding = ItemRulesMyToDoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyToDoViewHolder(binding, iconTypeHashMap, onClickIcon)
    }

    override fun onBindViewHolder(holder: MyToDoViewHolder, position: Int) {
        val data = currentList[position]
        holder.onBind(data)
    }

    companion object {
        private val MyTodoDiffUtilCallback =
            object : DiffUtil.ItemCallback<com.hous.data.entity.Rule>() {
                override fun areItemsTheSame(
                    oldItem: com.hous.data.entity.Rule,
                    newItem: com.hous.data.entity.Rule

                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: com.hous.data.entity.Rule,
                    newItem: com.hous.data.entity.Rule
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
