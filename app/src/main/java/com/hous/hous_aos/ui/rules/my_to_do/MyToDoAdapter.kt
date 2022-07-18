package com.hous.hous_aos.ui.rules.my_to_do

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.rules.MyToDoResponse
import com.hous.hous_aos.databinding.ItemRulesMyToDoBinding

class MyToDoAdapter(private val onClickIcon: (Int) -> Unit) :
    ListAdapter<MyToDoResponse, MyToDoAdapter.MyToDoViewHolder>(
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
        fun onBind(data: MyToDoResponse) {
            binding.data = data
            binding.iconType = iconTypeHashMap[data.categoryIcon]
            binding.ivCheckBox.setOnClickListener {
                val isSelected = binding.ivCheckBox.isSelected
                binding.ivCheckBox.isSelected = !isSelected
                onClickIcon(adapterPosition)
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
            object : DiffUtil.ItemCallback<MyToDoResponse>() {
                override fun areItemsTheSame(
                    oldItem: MyToDoResponse,
                    newItem: MyToDoResponse

                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MyToDoResponse,
                    newItem: MyToDoResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
