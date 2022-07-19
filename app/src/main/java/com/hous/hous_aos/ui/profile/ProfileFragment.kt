package com.hous.hous_aos.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentProfileBinding
import com.hous.hous_aos.util.setDrawable

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        initAdapter()
        initInfo()
        init()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        tagAdapter = null
    }

    private fun init() {
        binding.btnTendency.setOnClickListener {
            activity?.let {
                val intent = Intent(context, TestInfoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initInfo() {
        binding.profileData = ProfileData("이영주", "대학생", "개발이 가장 쉬웠어요")
    }

    private fun initAdapter() {
        tagAdapter = TagAdapter()
        binding.rvTag.adapter = tagAdapter
        requireNotNull(tagAdapter).submitList(
            listOf<TagData>(
                TagData("이빵주"),
                TagData("팬더"),
                TagData("안드로이드"),
            )
        )
    }
}
