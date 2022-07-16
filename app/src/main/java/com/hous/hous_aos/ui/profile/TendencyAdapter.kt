package com.hous.hous_aos.ui.profile

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemTendencyTestBinding

class TendencyAdapter(
    private val increase: () -> Unit,
    private val decrease: () -> Unit,
    private val setScore: (Int) -> Unit,
    private val visibilityButton: () -> Boolean,
) :
    ListAdapter<TendencyData, TendencyAdapter.TendencyViewHolder>(tendencyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TendencyViewHolder {
        val binding =
            ItemTendencyTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TendencyViewHolder(binding, increase, decrease, setScore, visibilityButton)
    }

    override fun onBindViewHolder(holder: TendencyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
    
    class TendencyViewHolder(
        private val binding: ItemTendencyTestBinding,
        private val increase: () -> Unit,
        private val decrease: () -> Unit,
        private val setScore: (Int) -> Unit,
        private val visibilityButton: () -> Boolean,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TendencyData) {
            binding.tendencyData = data
            binding.tvAnswer1.setOnClickListener {
                setScore(1)
                visibility()
            }
            binding.tvAnswer2.setOnClickListener {
                setScore(2)
                visibility()
            }
            binding.tvAnswer3.setOnClickListener {
                setScore(3)
                visibility()
            }
            binding.ivLeft.setOnClickListener {
                decrease()
                visibility()
            }
            binding.ivRight.setOnClickListener {
                increase()
                visibility()
            }
        }
        private fun visibility(){
            binding.ivRight.visibility = if(visibilityButton()) {
                View.VISIBLE
                Log.d("setScore","true ${View.VISIBLE}")
            } else {
                View.INVISIBLE
                Log.d("setScore","false ${View.INVISIBLE}")
            }
            Log.d("setScore", "binding ${binding.ivRight.visibility}, button ${visibilityButton()}")
        }
    }

    companion object {
        private val tendencyDiffUtil = object : DiffUtil.ItemCallback<TendencyData>() {
            override fun areItemsTheSame(oldItem: TendencyData, newItem: TendencyData): Boolean =
                oldItem.index == newItem.index

            override fun areContentsTheSame(oldItem: TendencyData, newItem: TendencyData): Boolean =
                oldItem == newItem
        }
    }
}
