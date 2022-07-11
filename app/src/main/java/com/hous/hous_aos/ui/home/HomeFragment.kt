package com.hous.hous_aos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var comingUpAdapter: ComingUpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        comingUpAdapter = ComingUpAdapter()
        binding.rvComingUp.adapter = comingUpAdapter
        comingUpAdapter.comingUpList.addAll(
            listOf(
                ComingUpData(R.drawable.ic_plus, "D-1"),
                ComingUpData(R.drawable.ic_party, "D-4"),
                ComingUpData(R.drawable.ic_beer, "D-6"),
                ComingUpData(R.drawable.ic_coffee, "D-10"),
                ComingUpData(R.drawable.ic_pancake, "D-15"),
                ComingUpData(R.drawable.ic_party, "D-18"),
                ComingUpData(R.drawable.ic_coffee, "D-20"),
                ComingUpData(R.drawable.ic_beer, "D-25"),
                ComingUpData(R.drawable.ic_pancake, "D-80"),
            )
        )
        comingUpAdapter.notifyDataSetChanged()
    }
}