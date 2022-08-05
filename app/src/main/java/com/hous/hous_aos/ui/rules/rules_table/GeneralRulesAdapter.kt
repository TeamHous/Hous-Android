package com.hous.hous_aos.ui.rules.rules_table

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.data.entity.Rule
import com.hous.hous_aos.databinding.ItemRulesTableGeneralBinding
import com.hous.hous_aos.ui.rules.IconColor

class GeneralRulesAdapter :
    ListAdapter<com.hous.data.entity.Rule, GeneralRulesAdapter.GeneralRulesViewHolder>(generalRulesDiffUtil) {

    private val iconColorHashMap: HashMap<String, IconColor> = hashMapOf(
        "RED" to IconColor.RED,
        "BLUE" to IconColor.BLUE,
        "GREEN" to IconColor.GREEN,
        "YELLOW" to IconColor.YELLOW,
        "GRAY" to IconColor.GRAY,
        "PURPLE" to IconColor.PURPLE,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralRulesViewHolder {
        val binding =
            ItemRulesTableGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeneralRulesViewHolder(binding, iconColorHashMap)
    }

    override fun onBindViewHolder(holder: GeneralRulesViewHolder, position: Int) {
        val currentItem = currentList[position]
        Log.d("크기", "$currentList")
        Log.d("크기", "${currentList.size}")
        holder.onBind(currentItem)
    }

    class GeneralRulesViewHolder(
        private val binding: ItemRulesTableGeneralBinding,
        private val iconColorHashMap: HashMap<String, IconColor>
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: com.hous.data.entity.Rule) {
            binding.data = data
            val memberCnt = if (data.membersCnt >= 4) 3 else data.membersCnt
            Log.d(
                "GeneralAdapter",
                "memberCnt:$memberCnt , data.iconList.size 값 : ${data.typeColors.size} ,data.iconList: ${data.typeColors} 이 들어왔습니다."
            )
            when (memberCnt) {
                MEMBER_COUNT_NONE -> binding.iconColorOne = IconColor.NONE
                MEMBER_COUNT_ONE -> {
                    binding.iconColorOne = getIconColor(data.typeColors[0])
                }
                MEMBER_COUNT_TWO -> {
                    binding.iconColorOne = getIconColor(data.typeColors[0])
                    binding.iconColorTwo = getIconColor(data.typeColors[1])
                }
                MEMBER_COUNT_TREE_OVER -> {
                    binding.iconColorOne = getIconColor(data.typeColors[0])
                    binding.iconColorTwo = getIconColor(data.typeColors[1])
                    binding.iconColorThree = getIconColor(data.typeColors[2])
                }
                else -> {
                    throw IllegalArgumentException("잘못된 data.iconList.size 값 : ${data.typeColors.size} ,data.iconList: ${data.typeColors} 이 들어왔습니다.")
                }
            }
        }

        private fun getIconColor(iconColorName: String): IconColor {
            return requireNotNull(iconColorHashMap[iconColorName])
        }
    }

    companion object {
        private val generalRulesDiffUtil = object : DiffUtil.ItemCallback<com.hous.data.entity.Rule>() {
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
        private const val MEMBER_COUNT_NONE = 0
        private const val MEMBER_COUNT_ONE = 1
        private const val MEMBER_COUNT_TWO = 2
        private const val MEMBER_COUNT_TREE_OVER = 3
    }
}
