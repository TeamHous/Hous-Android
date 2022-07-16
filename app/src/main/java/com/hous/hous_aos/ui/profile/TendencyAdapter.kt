package com.hous.hous_aos.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemTendencyTestBinding

class TendencyAdapter(
    private val select: (Int, TypeState) -> Unit,
    private val back: () -> Unit
) :
    ListAdapter<TypeTest, TendencyAdapter.TendencyViewHolder>(tendencyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TendencyViewHolder {
        val binding =
            ItemTendencyTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TendencyViewHolder(binding, select, back)
    }

    override fun onBindViewHolder(holder: TendencyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class TendencyViewHolder(
        private val binding: ItemTendencyTestBinding,
        private val select: (Int, TypeState) -> Unit,
        private val back: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TypeTest) {
            binding.data = data
            binding.tvAnswer1.setOnClickListener {
                select(adapterPosition, TypeState.ONE)
            }
            binding.ivLeft.setOnClickListener {
                back()
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
