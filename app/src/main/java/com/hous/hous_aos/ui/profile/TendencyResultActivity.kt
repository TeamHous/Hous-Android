package com.hous.hous_aos.ui.profile

import android.os.Bundle
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

        binding.tvEnd.setOnClickListener {
            finish()
        }
    }
}