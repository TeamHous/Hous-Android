package com.hous.hous_aos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ItemHomeHomieBinding
import com.hous.hous_aos.databinding.ItemHomeHomieCopyBinding
import com.hous.hous_aos.ui.home.HomieData

class HomieAdapter(
    private val showToast: () -> Unit
) :
    ListAdapter<HomieData, RecyclerView.ViewHolder>(homieDiffUtil) {

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
                HomieViewHolder(itemHomeHomieBinding)
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

    class HomieViewHolder(val binding: ItemHomeHomieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomieData) {
            binding.homie = data
        }
    }

    class CopyViewHolder(
        val binding: ItemHomeHomieCopyBinding,
        private val showToast: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            //코드부분
        }
    }

    companion object {
        private val homieDiffUtil = object : DiffUtil.ItemCallback<HomieData>() {
            override fun areItemsTheSame(oldItem: HomieData, newItem: HomieData): Boolean =
                oldItem.userName == newItem.userName

            override fun areContentsTheSame(oldItem: HomieData, newItem: HomieData): Boolean =
                oldItem == newItem
        }

        private const val HOMIE_ITEM = 0
        private const val COPY_ITEM = 1
    }
}