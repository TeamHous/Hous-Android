package com.hous.hous_aos.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemTendencyTestBinding

class TendencyAdapter(
    private val select: (Int, com.hous.data.entity.TypeState) -> Unit,
    private val backPage: () -> Unit,
    private val nextPage: () -> Unit,
    private val sendData: () -> Unit,
    private val showDialog: () -> Unit
) :
    ListAdapter<com.hous.data.entity.TypeTest, TendencyAdapter.TendencyViewHolder>(tendencyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TendencyViewHolder {
        val binding =
            ItemTendencyTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TendencyViewHolder(binding, select, backPage, nextPage, sendData, showDialog)
    }

    override fun onBindViewHolder(holder: TendencyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class TendencyViewHolder(
        private val binding: ItemTendencyTestBinding,
        private val select: (Int, com.hous.data.entity.TypeState) -> Unit,
        private val backPage: () -> Unit,
        private val nextPage: () -> Unit,
        private val sendData: () -> Unit,
        private val showDialog: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(typeTest: com.hous.data.entity.TypeTest) {
            with(binding) {
                data = typeTest
                tvAnswer1.setOnClickListener {
                    select(adapterPosition, com.hous.data.entity.TypeState.ONE)
                    if (typeTest.testNum == 15) {
                        sendData()
                    }
                }
                tvAnswer2.setOnClickListener {
                    select(adapterPosition, com.hous.data.entity.TypeState.TWO)
                    if (typeTest.testNum == 15) {
                        sendData()
                    }
                }
                tvAnswer3.setOnClickListener {
                    select(adapterPosition, com.hous.data.entity.TypeState.THREE)
                    if (typeTest.testNum == 15) {
                        sendData()
                    }
                }

                ivLeft.setOnClickListener { backPage() }
                ivRight.setOnClickListener { nextPage() }

                tvAnswer1.isSelected = typeTest.type == com.hous.data.entity.TypeState.ONE
                tvAnswer2.isSelected = typeTest.type == com.hous.data.entity.TypeState.TWO
                tvAnswer3.isSelected = typeTest.type == com.hous.data.entity.TypeState.THREE

                tvQuit.setOnClickListener { showDialog() }
            }
        }
    }

    companion object {
        private val tendencyDiffUtil = object : DiffUtil.ItemCallback<com.hous.data.entity.TypeTest>() {
            override fun areItemsTheSame(oldItem: com.hous.data.entity.TypeTest, newItem: com.hous.data.entity.TypeTest): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: com.hous.data.entity.TypeTest, newItem: com.hous.data.entity.TypeTest): Boolean =
                oldItem == newItem
        }
    }
}
