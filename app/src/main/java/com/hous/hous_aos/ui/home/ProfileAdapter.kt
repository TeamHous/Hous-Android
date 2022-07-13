package com.hous.hous_aos.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hous.hous_aos.databinding.ItemHomeProfileBinding

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    val profileList = mutableListOf<ProfileData>(ProfileData("이영주"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding =
            ItemHomeProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(profileList[position])
    }

    override fun getItemCount(): Int = profileList.size

    class ProfileViewHolder(
        private val binding: ItemHomeProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProfileData) {
            binding.profileData = data
        }
    }
}