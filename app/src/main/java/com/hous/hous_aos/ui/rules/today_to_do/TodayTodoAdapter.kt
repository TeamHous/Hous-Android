package com.hous.hous_aos.ui.rules.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.data.entity.rules.ManagerData
import com.hous.hous_aos.data.entity.rules.TodayTodoResponse
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemMultiBinding
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemNoneBinding
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemOneBinding
import com.hous.hous_aos.ui.rules.IconColor
import com.hous.hous_aos.ui.rules.today_to_do.ItemToDoViewType

class TodayTodoAdapter(private val onClickIcon: () -> Unit) :
    ListAdapter<TodayTodoResponse, RecyclerView.ViewHolder>(
        TodayTodoDiffUtilCallback
    ) {

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].number == MANAGER_NUMBER_ZERO) {
            ItemToDoViewType.NONE_MANAGER_VIEW_TYPE.index
        } else if (currentList[position].number == MANAGER_NUMBER_ONE) {
            ItemToDoViewType.ONE_MANAGER_VIEW_TYPE.index
        } else if (currentList[position].number >= MANAGER_NUMBER_TWO) {
            ItemToDoViewType.MUTI_MANAGER_VIEW_TYPE.index
        } else throw IllegalArgumentException("잘못된 position:$position 이 들어왔습니다.")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemToDoViewType.NONE_MANAGER_VIEW_TYPE.index -> NoneManagerViewHolder(
                onClickIcon,
                ItemRulesTodayToDoItemNoneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemToDoViewType.ONE_MANAGER_VIEW_TYPE.index -> OneManagerViewHolder(
                onClickIcon,
                ItemRulesTodayToDoItemOneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemToDoViewType.MUTI_MANAGER_VIEW_TYPE.index -> MultiManagerViewHolder(
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
        private val onClickIcon: () -> Unit,
        private val binding: ItemRulesTodayToDoItemNoneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodayTodoResponse) {
            binding.data = data
            binding.ivManagerEmpty.setOnClickListener {
                onClickIcon()
            }
        }
    }

    class OneManagerViewHolder(
        private val onClickIcon: () -> Unit,
        private val binding: ItemRulesTodayToDoItemOneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodayTodoResponse) {
            binding.data = data
            binding.tvManager.text = changeListToString(requireNotNull(data.managerDataList))
            binding.iconColor = getIconColor(data.iconList[0])
            binding.ivManager.setOnClickListener {
                onClickIcon()
            }
        }

        private fun changeListToString(managerDataList: List<ManagerData>): String {
            val textList = managerDataList.map { it.name }
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
        private val onClickIcon: () -> Unit,
        private val binding: ItemRulesTodayToDoItemMultiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodayTodoResponse) {
            binding.data = data
            binding.tvManager.text = changeListToString(requireNotNull(data.managerDataList))
            binding.clManagerIcon.setOnClickListener {
                onClickIcon()
            }
            when (data.iconList.size) {
                ICON_LIST_SIZE_TWO -> {
                    binding.count = ICON_LIST_SIZE_TWO
                    binding.iconColorOne = getIconColor(data.iconList[0])
                    binding.iconColorTwo = getIconColor(data.iconList[1])
                }
                ICON_LIST_SIZE_THREE -> {
                    binding.count = ICON_LIST_SIZE_THREE
                    binding.iconColorOne = getIconColor(data.iconList[0])
                    binding.iconColorTwo = getIconColor(data.iconList[1])
                    binding.iconColorThree = getIconColor(data.iconList[2])
                }
                ICON_LIST_SIZE_OVER_FOUR -> {
                    binding.count = ICON_LIST_SIZE_OVER_FOUR
                    binding.iconColorOne = getIconColor(data.iconList[0])
                    binding.iconColorTwo = getIconColor(data.iconList[1])
                    binding.iconColorThree = getIconColor(data.iconList[2])
                    binding.iconColorFour = getIconColor(data.iconList[3])
                }
                else -> throw IllegalArgumentException("잘못된 data.iconList.size 값 : ${data.iconList.size}이 들어왔습니다.")
            }
        }

        private fun changeListToString(managerDataList: List<ManagerData>): String {
            val textList = managerDataList.map { it.name }
            val sizeOfTextList = textList.size
            return if (sizeOfTextList in 2..3) {
                val text = textList.joinToString(separator = ",")
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
            object : DiffUtil.ItemCallback<TodayTodoResponse>() {
                override fun areItemsTheSame(
                    oldItem: TodayTodoResponse,
                    newItem: TodayTodoResponse

                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: TodayTodoResponse,
                    newItem: TodayTodoResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
        private const val MANAGER_NUMBER_ZERO = 0
        private const val MANAGER_NUMBER_ONE = 1
        private const val MANAGER_NUMBER_TWO = 2
        private const val ICON_LIST_SIZE_TWO = 2
        private const val ICON_LIST_SIZE_THREE = 3
        private const val ICON_LIST_SIZE_OVER_FOUR = 4
        private const val BLUE = "blue"
        private const val RED = "red"
        private const val PURPLE = "purple"
        private const val GRAY = "gray"
        private const val YELLOW = "yellow"
        private const val GREEN = "green"
    }
}
