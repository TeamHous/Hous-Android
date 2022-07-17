package com.hous.hous_aos.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemTendencyTestBinding

class TendencyAdapter(
    private val select: (Int, TypeState) -> Unit,
    private val backPage: () -> Unit,
    private val nextPage: () -> Unit,
    private val sendData: () -> Unit,
    private val showDialog: () -> Unit
) :
    ListAdapter<TypeTest, TendencyAdapter.TendencyViewHolder>(tendencyDiffUtil) {

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
        private val select: (Int, TypeState) -> Unit,
        private val backPage: () -> Unit,
        private val nextPage: () -> Unit,
        private val sendData: () -> Unit,
        private val showDialog: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(typeTest: TypeTest) {
            with(binding) {
                data = typeTest
                tvAnswer1.setOnClickListener {
                    select(adapterPosition, TypeState.ONE)
                    if (typeTest.testNum == 15) {
                        sendData()
                    }
                }
                tvAnswer2.setOnClickListener {
                    select(adapterPosition, TypeState.TWO)
                    if (typeTest.testNum == 15) {
                        sendData()
                    }
                }
                tvAnswer3.setOnClickListener {
                    select(adapterPosition, TypeState.THREE)
                    if (typeTest.testNum == 15) {
                        sendData()
                    }
                }

                ivLeft.setOnClickListener { backPage() }
                ivRight.setOnClickListener { nextPage() }

                tvAnswer1.isSelected = typeTest.type == TypeState.ONE
                tvAnswer2.isSelected = typeTest.type == TypeState.TWO
                tvAnswer3.isSelected = typeTest.type == TypeState.THREE

                tvQuit.setOnClickListener { showDialog() }
            }
        }
    }

    companion object {
        private val tendencyDiffUtil = object : DiffUtil.ItemCallback<TypeTest>() {
            override fun areItemsTheSame(oldItem: TypeTest, newItem: TypeTest): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: TypeTest, newItem: TypeTest): Boolean =
                oldItem == newItem
        }
    }
}
