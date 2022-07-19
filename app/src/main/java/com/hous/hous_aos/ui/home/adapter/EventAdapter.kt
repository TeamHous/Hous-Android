package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ItemHomeEventBinding
import com.hous.hous_aos.ui.home.EventData

class EventAdapter(
    private val onClickEventIcon: (Int) -> Unit
) :
    ListAdapter<EventData, EventAdapter.EventViewHolder>(eventDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding =
            ItemHomeEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, onClickEventIcon)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class EventViewHolder(
        val binding: ItemHomeEventBinding,
        private val onClickEventIcon: (Int) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: EventData) {
            binding.event = data
            binding.position = absoluteAdapterPosition
            when (data.eventIcon) {
                "NONE" -> {
                    binding.ivEvent.setImageResource(R.drawable.ic_plus)
                    binding.ivEventBg.setBackgroundResource(R.drawable.shape_yellow_bg_fill_16_rect)
                }
                "PARTY" -> {
                    binding.ivEvent.setImageResource(R.drawable.ic_party)
                    binding.clEvent.setBackgroundResource(R.drawable.shape_yellow_home_fill_16_rect)
                }
                "CAKE" -> {
                    binding.ivEvent.setImageResource(R.drawable.ic_pancake)
                    binding.clEvent.setBackgroundResource(R.drawable.shape_yellow_home_fill_16_rect)
                }
                "BEER" -> {
                    binding.ivEvent.setImageResource(R.drawable.ic_beer)
                    binding.clEvent.setBackgroundResource(R.drawable.shape_yellow_home_fill_16_rect)
                }
                "COFFEE" -> {
                    binding.ivEvent.setImageResource(R.drawable.ic_coffee)
                    binding.clEvent.setBackgroundResource(R.drawable.shape_yellow_home_fill_16_rect)
                }
            }
            binding.clEvent.setOnClickListener {
                onClickEventIcon(absoluteAdapterPosition)
            }
        }
    }

    companion object {
        private val eventDiffUtil = object : DiffUtil.ItemCallback<EventData>() {
            override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean =
                oldItem.dDay == newItem.dDay

            override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean =
                oldItem == newItem
        }
    }
}
