package com.hous.hous_aos.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.R
import com.hous.hous_aos.data.entity.Event
import com.hous.hous_aos.databinding.ItemHomeEventBinding

class EventAdapter(
    private val onClickEventIcon: (Int) -> Unit
) :
    ListAdapter<Event, EventAdapter.EventViewHolder>(eventDiffUtil) {

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
        fun onBind(data: Event) {
            binding.event = data
            binding.position = absoluteAdapterPosition
            when (data.eventIcon) {
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
                else -> {
                    binding.ivEvent.setImageResource(R.drawable.ic_plus)
                    binding.ivEventBg.setBackgroundResource(R.drawable.shape_yellow_bg_fill_16_rect)
                }
            }
            binding.clEvent.setOnClickListener {
                Log.d(TAG, "                    $absoluteAdapterPosition")
                onClickEventIcon(absoluteAdapterPosition)
            }
        }
    }

    companion object {
        private val eventDiffUtil = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
                oldItem == newItem
        }
        private val TAG = "dialod"
    }
}
