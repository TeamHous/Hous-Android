package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.data.entity.Homie
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ItemHomeHomieBinding
import com.hous.hous_aos.databinding.ItemHomeHomieCopyBinding

class HomieAdapter(
    private val showToast: () -> Unit,
    private val onClickHomie: (Int) -> Unit
) :
    ListAdapter<Homie, RecyclerView.ViewHolder>(homieDiffUtil) {

    private lateinit var itemHomeHomieBinding: ItemHomeHomieBinding
    private lateinit var itemHomeHomieCopyBinding: ItemHomeHomieCopyBinding

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            currentList.size - 1 -> COPY_ITEM
            else -> HOMIE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOMIE_ITEM -> {
                itemHomeHomieBinding =
                    ItemHomeHomieBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HomieViewHolder(itemHomeHomieBinding, onClickHomie)
            }
            COPY_ITEM -> {
                itemHomeHomieCopyBinding =
                    ItemHomeHomieCopyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                CopyViewHolder(itemHomeHomieCopyBinding, showToast)
            }
            else -> {
                throw RuntimeException("알 수 없는 viewType error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (holder) {
            is HomieViewHolder -> holder.onBind(data)
            is CopyViewHolder -> holder.onBind()
        }
    }

    class HomieViewHolder(
        val binding: ItemHomeHomieBinding,
        private val onClickHomie: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Homie) {
            itemView.setOnClickListener {
                onClickHomie(absoluteAdapterPosition)
            }

            binding.homie = data
            when (data.typeColor) {
                "YELLOW" -> {
                    binding.clHomie.setBackgroundResource(R.drawable.shape_yellow_bg_fill_10_rect)
                    binding.ivHomie.setImageResource(R.drawable.ic_s_yellow)
                }
                "GREEN" -> {
                    binding.clHomie.setBackgroundResource(R.drawable.shape_green_bg_fill_10_rect)
                    binding.ivHomie.setImageResource(R.drawable.ic_s_green)
                }
                "RED" -> {
                    binding.clHomie.setBackgroundResource(R.drawable.shape_red_bg_fill_10_rect)
                    binding.ivHomie.setImageResource(R.drawable.ic_s_red)
                }
                "BLUE" -> {
                    binding.clHomie.setBackgroundResource(R.drawable.shape_blue_bg_fill_10_rect)
                    binding.ivHomie.setImageResource(R.drawable.ic_s_blue)
                }
                "PURPLE" -> {
                    binding.clHomie.setBackgroundResource(R.drawable.shape_purple_bg_fill_10_rect)
                    binding.ivHomie.setImageResource(R.drawable.ic_s_purple)
                }
                else -> {
                    binding.clHomie.setBackgroundResource(R.drawable.shape_g2_fill_10_rect)
                    binding.ivHomie.setImageResource(R.drawable.ic_s_gray)
                }
            }
        }
    }

    class CopyViewHolder(
        val binding: ItemHomeHomieCopyBinding,
        private val showToast: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            itemView.setOnClickListener {
                showToast()
            }
        }
    }

    companion object {
        private val homieDiffUtil = object : DiffUtil.ItemCallback<Homie>() {
            override fun areItemsTheSame(oldItem: Homie, newItem: Homie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Homie, newItem: Homie): Boolean =
                oldItem == newItem
        }

        private const val HOMIE_ITEM = 0
        private const val COPY_ITEM = 1
    }
}
