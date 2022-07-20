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

        var data = intent.getStringExtra("data")

        when(data){
            "end" -> {
                binding.ivBack.visibility = View.INVISIBLE
                binding.tvEnd.visibility = View.VISIBLE
            }
            "back" -> {
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
            if (data.good.typeName == "동글이") {
                binding.tvGoodName.text = "행복한 동글이"
                binding.clGood.backgroundTintList = this.getColorStateList(R.color.hous_yellow_bg)
            }
            if (data.bad.typeName == "사각이") {
                binding.tvBadName.text = "불행한 사각이"
                binding.clBad.backgroundTintList = this.getColorStateList(R.color.hous_blue_bg)
            }
            if (data.typeColor == "GREEN") {
                binding.tvComment.setTextColor(ContextCompat.getColor(this, R.color.hous_green))
                binding.tvRulesTitle.setTextColor(ContextCompat.getColor(this, R.color.hous_green))
                binding.tvRulesTitle.backgroundTintList =
                    this.getColorStateList(R.color.hous_green_bg)
                binding.clComment.backgroundTintList = this.getColorStateList(R.color.hous_green_bg)
                binding.clRules.backgroundTintList = this.getColorStateList(R.color.hous_green_bg)
                binding.ivShape1.setDrawable(R.drawable.ic_hexagon)
                binding.ivShape2.setDrawable(R.drawable.ic_hexagon)
            }
        }
    }
}