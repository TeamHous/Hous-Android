package com.hous.hous_aos.ui.profile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityTendencyTestBinding

class TendencyTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTendencyTestBinding
    private lateinit var tendencyAdapter: TendencyAdapter
    val viewModel: TendencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tendency_test)
        setContentView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        tendencyAdapter = TendencyAdapter()
        binding.vpTendency.adapter = tendencyAdapter
        binding.vpTendency.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpTendency.isUserInputEnabled = false

        viewModel.uiState.observe(this) { tendencyAdapter.submitList(it) }

        viewModel.move.observe(this) { move ->
            if (move) {
                binding.vpTendency.currentItem += 1
            } else {
                binding.vpTendency.currentItem -= 1
            }
        }
    }
}
