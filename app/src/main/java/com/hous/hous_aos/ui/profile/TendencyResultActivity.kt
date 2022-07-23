package com.hous.hous_aos.ui.profile

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityTendencyResultBinding
import com.hous.hous_aos.util.setDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TendencyResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTendencyResultBinding
    val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tendency_result)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.vm = viewModel
        binding.lifecycleOwner = this

        val userId = intent.getStringExtra("userId")
        viewModel.userId.value = userId

        val data = intent.getStringExtra("data")
        when (data) {
            "end" -> {
                viewModel.myResult()
                binding.ivBack.visibility = View.INVISIBLE
                binding.tvEnd.visibility = View.VISIBLE
            }
            "back" -> {
                viewModel.homieResult()
                binding.ivBack.visibility = View.VISIBLE
                binding.tvEnd.visibility = View.INVISIBLE
            }
        }

        binding.tvEnd.setOnClickListener {
            finish()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        viewModel.resultData.observe(this) { data ->
            when (data.typeColor) {
                "GREEN" -> {
                    binding.clComment.backgroundTintList =
                        this.getColorStateList(R.color.hous_green_bg)
                    binding.clRules.backgroundTintList =
                        this.getColorStateList(R.color.hous_green_bg)
                    binding.tvComment.setTextColor(ContextCompat.getColor(this, R.color.hous_green))
                    binding.tvRulesTitle.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_green
                        )
                    )
                    binding.tvRulesTitle.backgroundTintList =
                        this.getColorStateList(R.color.hous_green_bg)
                    binding.ivShape1.setDrawable(R.drawable.ic_hexagon)
                    binding.ivShape2.setDrawable(R.drawable.ic_hexagon)
                }
                "PURPLE" -> {
                    binding.clComment.backgroundTintList =
                        this.getColorStateList(R.color.hous_purple_bg)
                    binding.clRules.backgroundTintList =
                        this.getColorStateList(R.color.hous_purple_bg)
                    binding.tvComment.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_purple
                        )
                    )
                    binding.tvRulesTitle.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_purple
                        )
                    )
                    binding.tvRulesTitle.backgroundTintList =
                        this.getColorStateList(R.color.hous_purple_bg)
                    binding.ivShape1.setDrawable(R.drawable.ic_pentagon)
                    binding.ivShape2.setDrawable(R.drawable.ic_pentagon)
                }
                "BLUE" -> {
                    binding.clComment.backgroundTintList =
                        this.getColorStateList(R.color.hous_blue_bg)
                    binding.clRules.backgroundTintList =
                        this.getColorStateList(R.color.hous_blue_bg)
                    binding.tvComment.setTextColor(ContextCompat.getColor(this, R.color.hous_blue))
                    binding.tvRulesTitle.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_blue
                        )
                    )
                    binding.tvRulesTitle.backgroundTintList =
                        this.getColorStateList(R.color.hous_blue_bg)
                    binding.ivShape1.setDrawable(R.drawable.ic_square)
                    binding.ivShape2.setDrawable(R.drawable.ic_square)
                }
                "RED" -> {
                    binding.clComment.backgroundTintList =
                        this.getColorStateList(R.color.hous_red_bg)
                    binding.clRules.backgroundTintList = this.getColorStateList(R.color.hous_red_bg)
                    binding.tvComment.setTextColor(ContextCompat.getColor(this, R.color.hous_red))
                    binding.tvRulesTitle.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_red
                        )
                    )
                    binding.tvRulesTitle.backgroundTintList =
                        this.getColorStateList(R.color.hous_red_bg)
                    binding.ivShape1.setDrawable(R.drawable.ic_triangle)
                    binding.ivShape2.setDrawable(R.drawable.ic_triangle)
                }
                "YELLOW" -> {
                    binding.clComment.backgroundTintList =
                        this.getColorStateList(R.color.hous_yellow_bg)
                    binding.clRules.backgroundTintList =
                        this.getColorStateList(R.color.hous_yellow_bg)
                    binding.tvComment.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_yellow
                        )
                    )
                    binding.tvRulesTitle.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.hous_yellow
                        )
                    )
                    binding.tvRulesTitle.backgroundTintList =
                        this.getColorStateList(R.color.hous_yellow_bg)
                    binding.ivShape1.setDrawable(R.drawable.ic_circle)
                    binding.ivShape2.setDrawable(R.drawable.ic_circle)
                }
            }
            when (data.bad.typeName) {
                "슈퍼 팔로워 셋돌이" -> {
                    binding.clBad.backgroundTintList = this.getColorStateList(R.color.hous_red_bg)
                    binding.tvBad.setTextColor(ContextCompat.getColor(this, R.color.hous_red))
                }
                "늘 행복한 동글이" -> {
                    binding.clBad.backgroundTintList = this.getColorStateList(R.color.hous_yellow_bg)
                    binding.tvBad.setTextColor(ContextCompat.getColor(this, R.color.hous_yellow))
                }
                "룸메 맞춤형 네각이" -> {
                    binding.clBad.backgroundTintList = this.getColorStateList(R.color.hous_blue_bg)
                    binding.tvBad.setTextColor(ContextCompat.getColor(this, R.color.hous_blue))
                }
                "하이 레벨 오각이" -> {
                    binding.clBad.backgroundTintList = this.getColorStateList(R.color.hous_purple_bg)
                    binding.tvBad.setTextColor(ContextCompat.getColor(this, R.color.hous_purple))
                }
                "룰 세터 육각이" -> {
                    binding.clBad.backgroundTintList = this.getColorStateList(R.color.hous_green_bg)
                    binding.tvBad.setTextColor(ContextCompat.getColor(this, R.color.hous_green))
                }
            }
            when (data.good.typeName) {
                "슈퍼 팔로워 셋돌이" -> {
                    binding.clGood.backgroundTintList = this.getColorStateList(R.color.hous_red_bg)
                    binding.tvGood.setTextColor(ContextCompat.getColor(this, R.color.hous_red))
                }
                "늘 행복한 동글이" -> {
                    binding.clGood.backgroundTintList = this.getColorStateList(R.color.hous_yellow_bg)
                    binding.tvGood.setTextColor(ContextCompat.getColor(this, R.color.hous_yellow))
                }
                "룸메 맞춤형 네각이" -> {
                    binding.clGood.backgroundTintList = this.getColorStateList(R.color.hous_blue_bg)
                    binding.tvGood.setTextColor(ContextCompat.getColor(this, R.color.hous_blue))
                }
                "하이 레벨 오각이" -> {
                    binding.clGood.backgroundTintList = this.getColorStateList(R.color.hous_purple_bg)
                    binding.tvGood.setTextColor(ContextCompat.getColor(this, R.color.hous_purple))
                }
                "룰 세터 육각이" -> {
                    binding.clGood.backgroundTintList = this.getColorStateList(R.color.hous_green_bg)
                    binding.tvGood.setTextColor(ContextCompat.getColor(this, R.color.hous_green))
                }
            }
        }
    }
}