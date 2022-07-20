package com.hous.hous_aos.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentProfileBinding
import com.hous.hous_aos.ui.newrules.component.TendencyBox
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
        initInfo()
        init()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.cvProfilePentagon.setContent {
//            if (viewModel.profileData.value!!.typeColor == "GRAY") {
            TendencyBox { activity?.finish() }
//            } else {
//                PentagonBox(
//                    typeName = viewModel.profileData.value!!.typeName,
//                    typeColor = viewModel.profileData.value!!.typeColor,
//                    typeScore = viewModel.profileData.value!!.typeScore
//                )
//            }
        }
    }

    private fun initInfo() {
        viewModel.profileData.observe(viewLifecycleOwner) {
            when (it.hashTag.size) {
                0 -> {
                    binding.tvHashtag1.visibility = View.GONE
                    binding.tvHashtag2.visibility = View.GONE
                    binding.tvHashtag3.visibility = View.GONE
                }
                1 -> {
                    binding.tvHashtag1.text = it.hashTag[0]
                    binding.tvHashtag2.visibility = View.GONE
                    binding.tvHashtag3.visibility = View.GONE
                }
                2 -> {
                    binding.tvHashtag1.text = it.hashTag[0]
                    binding.tvHashtag2.text = it.hashTag[1]
                    binding.tvHashtag3.visibility = View.GONE
                }
                3 -> {
                    binding.tvHashtag1.text = it.hashTag[0]
                    binding.tvHashtag2.text = it.hashTag[1]
                    binding.tvHashtag3.text = it.hashTag[2]
                }
            }

            when (it.typeColor) {
                "RED" -> {
                    binding.ivProfile.setDrawable(R.drawable.ic_profile_red)
                    binding.tvHashtag1.setBackgroundResource(R.drawable.shape_red_fill_8_rect)
                    binding.tvHashtag2.setBackgroundResource(R.drawable.shape_red_fill_8_rect)
                    binding.tvHashtag3.setBackgroundResource(R.drawable.shape_red_fill_8_rect)
                }
                "GREEN" -> {
                    binding.ivProfile.setDrawable(R.drawable.ic_profile_green)
                    binding.tvHashtag1.setBackgroundResource(R.drawable.shape_green_fill_8_rect)
                    binding.tvHashtag2.setBackgroundResource(R.drawable.shape_green_fill_8_rect)
                    binding.tvHashtag3.setBackgroundResource(R.drawable.shape_green_fill_8_rect)
                }
                "BLUE" -> {
                    binding.ivProfile.setDrawable(R.drawable.ic_profile_blue)
                    binding.tvHashtag1.setBackgroundResource(R.drawable.shape_blue_fill_8_rect)
                    binding.tvHashtag2.setBackgroundResource(R.drawable.shape_blue_fill_8_rect)
                    binding.tvHashtag3.setBackgroundResource(R.drawable.shape_blue_fill_8_rect)
                }
                "PURPLE" -> {
                    binding.ivProfile.setDrawable(R.drawable.ic_profile_purple)
                    binding.tvHashtag1.setBackgroundResource(R.drawable.shape_purple_fill_8_rect)
                    binding.tvHashtag2.setBackgroundResource(R.drawable.shape_purple_fill_8_rect)
                    binding.tvHashtag3.setBackgroundResource(R.drawable.shape_purple_fill_8_rect)
                }
                "YELLOW" -> {
                    binding.ivProfile.setDrawable(R.drawable.ic_profile_yellow)
                    binding.tvHashtag1.setBackgroundResource(R.drawable.shape_yellow_fill_8_rect)
                    binding.tvHashtag2.setBackgroundResource(R.drawable.shape_yellow_fill_8_rect)
                    binding.tvHashtag3.setBackgroundResource(R.drawable.shape_yellow_fill_8_rect)
                }
                else -> {
                    binding.ivProfile.setDrawable(R.drawable.ic_profile_default)
                    binding.tvHashtag1.setBackgroundResource(R.drawable.shape_red_bg2_fill_8_rect)
                    binding.tvHashtag2.setBackgroundResource(R.drawable.shape_red_bg2_fill_8_rect)
                    binding.tvHashtag3.setBackgroundResource(R.drawable.shape_red_bg2_fill_8_rect)
                    binding.tvDetailTendency.visibility = View.INVISIBLE
                }
            }
        }
    }
}
