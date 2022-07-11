package com.hous.hous_aos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeComingUpBinding

class ComingUpAdapter : RecyclerView.Adapter<ComingUpAdapter.ComingUpViewHolder>() {
    val comingUpList = mutableListOf<ComingUpData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingUpViewHolder {
        val binding =
            ItemHomeComingUpBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ComingUpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComingUpViewHolder, position: Int) {
        holder.onBind(comingUpList[position])
    }

    override fun getItemCount(): Int = comingUpList.size

    class ComingUpViewHolder(
        private val binding: ItemHomeComingUpBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ComingUpData) {
            binding.ivComingUpImage.setImageResource(data.image)
            binding.tvComingUpDay.text = data.day
            binding.position = adapterPosition
        }
    }
}