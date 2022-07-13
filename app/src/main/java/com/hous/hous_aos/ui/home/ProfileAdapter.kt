package com.hous.hous_aos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeProfileBinding
import com.hous.hous_aos.databinding.ItemHomeProfileCopyBinding

class ProfileAdapter() :
    ListAdapter<ProfileData, RecyclerView.ViewHolder>(profileDiffUtil) {

    private lateinit var itemHomeProfileBinding: ItemHomeProfileBinding
    private lateinit var itemHomeProfileLastBinding: ItemHomeProfileCopyBinding

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            currentList.size - 1 -> COPY_ITEM
            else -> PROFILE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PROFILE_ITEM -> {
                itemHomeProfileBinding =
                    ItemHomeProfileBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ProfileViewHolder(itemHomeProfileBinding)
            }
            COPY_ITEM -> {
                itemHomeProfileLastBinding =
                    ItemHomeProfileCopyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                CopyViewHolder(itemHomeProfileLastBinding)
            }
            else -> {
                throw RuntimeException("알 수 없는 viewType error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (holder) {
            is ProfileViewHolder -> holder.onBind(data)
            is CopyViewHolder -> holder.onBind(data)
        }
    }

    class ProfileViewHolder(val binding: ItemHomeProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProfileData) {
            binding.profileData = data
        }
    }

    class CopyViewHolder(val binding: ItemHomeProfileCopyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProfileData) {
            binding.profileData = data
        }
    }

    companion object {
        private val profileDiffUtil = object : DiffUtil.ItemCallback<ProfileData>() {
            override fun areItemsTheSame(oldItem: ProfileData, newItem: ProfileData): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ProfileData, newItem: ProfileData): Boolean =
                oldItem == newItem
        }

        private const val PROFILE_ITEM = 0
        private const val COPY_ITEM = 1
    }
}