package com.hous.hous_aos.ui.rules.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.R
import com.hous.hous_aos.data.entity.rules.ManagerData
import com.hous.hous_aos.data.entity.rules.TodayTodoResponse
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemMultiBinding
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemNoneBinding
import com.hous.hous_aos.databinding.ItemRulesTodayToDoItemOneBinding

class TodayTodoAdapter : ListAdapter<TodayTodoResponse, RecyclerView.ViewHolder>(
    TodayTodoDiffUtilCallback
) {

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].number == 0) {
            ItemToDoViewType.NONE_MANAGER_VIEW_TYPE.index
        } else if (currentList[position].number == 1) {
            ItemToDoViewType.ONE_MANAGER_VIEW_TYPE.index
        } else if (currentList[position].number >= 2) {
            ItemToDoViewType.MUTI_MANAGER_VIEW_TYPE.index
        } else throw IllegalArgumentException("잘못된 position:$position 이 들어왔습니다.")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemToDoViewType.NONE_MANAGER_VIEW_TYPE.index -> NoneManagerViewHolder(
                ItemRulesTodayToDoItemNoneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemToDoViewType.ONE_MANAGER_VIEW_TYPE.index -> OneManagerViewHolder(
                ItemRulesTodayToDoItemOneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ItemToDoViewType.MUTI_MANAGER_VIEW_TYPE.index -> MultiManagerViewHolder(
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
        private val binding: ItemRulesTodayToDoItemNoneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodayTodoResponse) {
            binding.data = data
        }
    }

    class OneManagerViewHolder(
        private val binding: ItemRulesTodayToDoItemOneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodayTodoResponse) {
            binding.data = data
            binding.tvManager.text = changeListToString(requireNotNull(data.managerDataList))
            binding.iconColor = getIconColor(data.iconList[0])
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
        private val binding: ItemRulesTodayToDoItemMultiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TodayTodoResponse) {
            binding.data = data
            binding.tvManager.text = changeListToString(requireNotNull(data.managerDataList))
            when (data.iconList.size) {
                2 -> {
                    binding.count = 2
                    binding.iconColorOne = getIconColor(data.iconList[0])
                    binding.iconColorTwo = getIconColor(data.iconList[1])
                }
                3 -> {
                    binding.count = 3
                    binding.iconColorOne = getIconColor(data.iconList[0])
                    binding.iconColorTwo = getIconColor(data.iconList[1])
                    binding.iconColorThree = getIconColor(data.iconList[2])
                }
                else -> {
                    binding.count = 4
                    binding.iconColorOne = getIconColor(data.iconList[0])
                    binding.iconColorTwo = getIconColor(data.iconList[1])
                    binding.iconColorThree = getIconColor(data.iconList[2])
                    binding.iconColorFour = getIconColor(data.iconList[3])
                }
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
        private const val BLUE = "blue"
        private const val RED = "red"
        private const val PURPLE = "purple"
        private const val GRAY = "gray"
        private const val YELLOW = "yellow"
        private const val GREEN = "green"
    }
}

/**
 * 뷰타입 이넘*/
enum class ItemToDoViewType(val index: Int) {
    NONE_MANAGER_VIEW_TYPE(0), ONE_MANAGER_VIEW_TYPE(1), MUTI_MANAGER_VIEW_TYPE(2)
}

/**
 * Icon 색 이넘*/
enum class IconColor(val colorRes: Int) {
    BLUE(R.color.hous_blue),
    RED(R.color.hous_red),
    GREEN(R.color.hous_green),
    YELLOW(R.color.hous_yellow),
    GRAY(R.color.g_3),
    PURPLE(R.color.hous_purple)
}
