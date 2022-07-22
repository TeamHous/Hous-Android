package com.hous.hous_aos.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityRoommateCardBinding
import com.hous.hous_aos.ui.pentagon.PentagonBox
import com.hous.hous_aos.ui.profile.TendencyResultActivity
import com.hous.hous_aos.util.setDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoommateCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoommateCardBinding
    val viewModel: RommateViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_roommate_card)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.homieId.value = intent.getStringExtra("position")

        viewModel.getHomieList()

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.clTypeDetail.setOnClickListener {
            val intent = Intent(this, TendencyResultActivity::class.java)
            intent.putExtra("data", "back") // "back"면 백버튼, "end"면 완료버튼
            intent.putExtra("userId", viewModel.homieId.value)
            startActivity(intent)
        }

        viewModel.homieData.observe(this) {
            binding.cvProfilePentagon.setContent {
                with(viewModel.homieData.value!!) {
                    PentagonBox(
                        typeName = typeName,
                        typeColor = typeColor,
                        typeScore = typeScore
                    )
                }
            }
            when (it.hashTag.size) {
                0 -> {
                    binding.tvHashtag1.visibility = View.INVISIBLE
                    binding.tvHashtag2.visibility = View.INVISIBLE
                    binding.tvHashtag3.visibility = View.INVISIBLE
                }
                1 -> {
                    binding.tvHashtag1.text = it.hashTag[0]
                    binding.tvHashtag2.visibility = View.INVISIBLE
                    binding.tvHashtag3.visibility = View.INVISIBLE
                }
                2 -> {
                    binding.tvHashtag1.text = it.hashTag[0]
                    binding.tvHashtag2.text = it.hashTag[1]
                    binding.tvHashtag3.visibility = View.INVISIBLE
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
                }
            }
        }
    }
}
