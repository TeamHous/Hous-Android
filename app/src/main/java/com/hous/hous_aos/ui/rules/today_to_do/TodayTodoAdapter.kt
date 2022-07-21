package com.hous.hous_aos.ui.rules.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemMultiBinding
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemNoneBinding
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemOneBinding
import com.hous.hous_aos.ui.rules.IconColor
import com.hous.hous_aos.ui.rules.today_to_do.ItemToDoViewType

class TodayTodoAdapter(
    private val onClickIcon: () -> Unit,
    private val fetchToTmpManagerList: (Int) -> Unit
) :
    ListAdapter<Rule, RecyclerView.ViewHolder>(
        TodayTodoDiffUtilCallback
    ) {

    override fun getItemViewType(position: Int): Int {
        val data = currentList[position]
        val managerCnt = data.todayMembersWithTypeColor.size
        return if (data.todayMembersWithTypeColor.isEmpty()) {
            ItemToDoViewType.NONE_MANAGER_VIEW_TYPE.index
        } else if (managerCnt == MANAGER_NUMBER_ONE) {
            ItemToDoViewType.ONE_MANAGER_VIEW_TYPE.index
        } else if (managerCnt >= MANAGER_NUMBER_TWO) {
            ItemToDoViewType.MUTI_MANAGER_VIEW_TYPE.index
        } else throw IllegalArgumentException("잘못된 position:$position 이 들어왔습니다.")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemToDoViewType.NONE_MANAGER_VIEW_TYPE.index -> NoneManagerViewHolder(
                fetchToTmpManagerList,
                onClickIcon,
                ItemRulesTodayToDoItemNoneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemToDoViewType.ONE_MANAGER_VIEW_TYPE.index -> OneManagerViewHolder(
                fetchToTmpManagerList,
                onClickIcon,
                ItemRulesTodayToDoItemOneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemToDoViewType.MUTI_MANAGER_VIEW_TYPE.index -> MultiManagerViewHolder(
                fetchToTmpManagerList,
                onClickIcon,
                ItemRulesTodayToDoItemMultiBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("잘못된 ViewType:$viewType 이 들어왔습니다.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (holder) {
            is NoneManagerViewHolder -> holder.onBind(data)
            is OneManagerViewHolder -> holder.onBind(data)
            is MultiManagerViewHolder -> holder.onBind(data)
        }
    }

    class NoneManagerViewHolder(
        private val fetchToTmpManagerList: (Int) -> Unit,
        private val onClickIcon: () -> Unit,
        private val binding: ItemRulesTodayToDoItemNoneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Rule) {
            Log.d(TAG, "NONE : ,  data: $data")
            binding.data = data
            binding.ivManagerEmpty.setOnClickListener {
                fetchToTmpManagerList(absoluteAdapterPosition)
                onClickIcon()
            }
        }
    }

    class OneManagerViewHolder(
        private val fetchToTmpManagerList: (Int) -> Unit,
        private val onClickIcon: () -> Unit,
        private val binding: ItemRulesTodayToDoItemOneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Rule) {
            Log.d(TAG, "One : ,  data: $data")
            binding.data = data
            binding.tvManager.text =
                changeListToString(requireNotNull(data.todayMembersWithTypeColor))
            binding.iconColor = getIconColor(data.todayMembersWithTypeColor[0].typeColor)
            binding.ivManager.setOnClickListener {
                fetchToTmpManagerList(absoluteAdapterPosition)
                onClickIcon()
            }
        }

        private fun changeListToString(managerDataList: List<Homie>): String {
            val textList = managerDataList.map { it.userName }
            return textList[0]
        }

        private fun getIconColor(iconName: String): IconColor {
            return when (iconName) {
                BLUE -> IconColor.BLUE
                RED -> IconColor.RED
                PURPLE -> IconColor.PURPLE
                GRAY -> IconColor.GRAY
                YELLOW -> IconColor.YELLOW
                GREEN -> IconColor.GREEN
                else -> throw IllegalArgumentException("IconName: $iconName error")
            }
        }
    }

    class MultiManagerViewHolder(
        private val fetchToTmpManagerList: (Int) -> Unit,
        private val onClickIcon: () -> Unit,
        private val binding: ItemRulesTodayToDoItemMultiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Rule) {
            Log.d(TAG, "MULTI : ,  data: $data")
            binding.data = data
            binding.tvManager.text =
                changeListToString(requireNotNull(data.todayMembersWithTypeColor))
            binding.clManagerIcon.setOnClickListener {
                fetchToTmpManagerList(absoluteAdapterPosition)
                onClickIcon()
            }
            val memberCnt = data.todayMembersWithTypeColor.size
            when (memberCnt) {
                2 -> {
                    binding.count = 2
                    binding.iconColorOne = getIconColor(data.todayMembersWithTypeColor[0].typeColor)
                    binding.iconColorTwo = getIconColor(data.todayMembersWithTypeColor[1].typeColor)
                }
                3 -> {
                    binding.count = 3
                    binding.iconColorOne = getIconColor(data.todayMembersWithTypeColor[0].typeColor)
                    binding.iconColorTwo = getIconColor(data.todayMembersWithTypeColor[1].typeColor)
                    binding.iconColorThree =
                        getIconColor(data.todayMembersWithTypeColor[2].typeColor)
                }
                4 -> {
                    binding.count = 4
                    binding.iconColorOne = getIconColor(data.todayMembersWithTypeColor[0].typeColor)
                    binding.iconColorTwo = getIconColor(data.todayMembersWithTypeColor[1].typeColor)
                    binding.iconColorThree =
                        getIconColor(data.todayMembersWithTypeColor[2].typeColor)
                    binding.iconColorFour =
                        getIconColor(data.todayMembersWithTypeColor[3].typeColor)
                }
                else -> throw IllegalArgumentException("잘못된 data.iconList.size 값 : ${memberCnt}이 들어왔습니다.")
            }
        }

        private fun changeListToString(managerDataList: List<Homie>): String {
            val textList = managerDataList.map { it.userName }
            val sizeOfTextList = textList.size
            return if (sizeOfTextList in 2..3) {
                val text = textList.joinToString(separator = ", ")
                text
            } else if (sizeOfTextList >= 4) {
                val restOfthePeopleCount = sizeOfTextList - 3
                val text =
                    textList.joinToString(separator = ", ", postfix = " 외 $restOfthePeopleCount 명")
                text
            } else {
                throw IllegalArgumentException("잘못된 값이 들어왔습니다. textList: $textList , sizeOfTextList: $sizeOfTextList")
            }
        }

        private fun getIconColor(iconName: String): IconColor {
            return when (iconName) {
                BLUE -> IconColor.BLUE
                RED -> IconColor.RED
                PURPLE -> IconColor.PURPLE
                GRAY -> IconColor.GRAY
                YELLOW -> IconColor.YELLOW
                GREEN -> IconColor.GREEN
                else -> throw IllegalArgumentException("IconName: $iconName error")
            }
        }
    }

    companion object {
        private val TodayTodoDiffUtilCallback =
            object : DiffUtil.ItemCallback<Rule>() {
                override fun areItemsTheSame(
                    oldItem: Rule,
                    newItem: Rule

                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Rule,
                    newItem: Rule
                ): Boolean {
                    return oldItem == newItem
                }
            }
        const val TAG = "로그"
        private const val MANAGER_NUMBER_ZERO = 0
        private const val MANAGER_NUMBER_ONE = 1
        private const val MANAGER_NUMBER_TWO = 2
        private const val BLUE = "BLUE"
        private const val RED = "RED"
        private const val PURPLE = "PURPLE"
        private const val GRAY = "GRAY"
        private const val YELLOW = "YELLOW"
        private const val GREEN = "GREEN"
    }
}
