package com.hous.hous_aos.ui.rules.today_to_do.tem_dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.rules.HomieData
import com.hous.hous_aos.databinding.ItemRulesTmpManagersBinding
import com.hous.hous_aos.ui.rules.HomieIconType

class TempMangerAdapter(val setSelectedTmpManager: (Int) -> Unit) :
    ListAdapter<HomieData, TempMangerAdapter.TmpManagerViewHolder>(tempManagerDiffUtil) {
    private val homieIconHashMap: HashMap<String, HomieIconType> = hashMapOf(
        "RED" to HomieIconType.RED,
        "BLUE" to HomieIconType.BLUE,
        "GREEN" to HomieIconType.GREEN,
        "YELLOW" to HomieIconType.YELLOW,
        "GRAY" to HomieIconType.GRAY,
        "PURPLE" to HomieIconType.PURPLE,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TmpManagerViewHolder {
        val binding =
            ItemRulesTmpManagersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TmpManagerViewHolder(binding, homieIconHashMap, setSelectedTmpManager)
    }

    override fun onBindViewHolder(holder: TmpManagerViewHolder, position: Int) {
        val currentItem = currentList[position]
        return holder.onBind(currentItem)
    }

    class TmpManagerViewHolder(
        private val binding: ItemRulesTmpManagersBinding,
        private val homieIconHashMap: HashMap<String, HomieIconType>,
        private val setSelectedTmpManager: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomieData) {
            binding.data = data
            binding.homieIconType = homieIconHashMap[data.typeColor]
            binding.ivManagerIcon.setOnClickListener {
                setSelectedTmpManager(absoluteAdapterPosition)
                val flag = binding.ivManagerIcon.isSelected
                binding.ivManagerIcon.isSelected = !flag
            }
        }
    }

    companion object {
        private val tempManagerDiffUtil = object : DiffUtil.ItemCallback<HomieData>() {
            override fun areItemsTheSame(
                oldItem: HomieData,
                newItem: HomieData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: HomieData,
                newItem: HomieData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
