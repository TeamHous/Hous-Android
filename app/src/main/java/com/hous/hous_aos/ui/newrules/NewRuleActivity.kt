package com.hous.hous_aos.ui.newrules

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityNewRuleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewRuleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewRuleBinding
    private val viewModel: NewRulesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryName = intent.getStringExtra("categoryName") ?: ""
        val categoryId = intent.getStringExtra("categoryId") ?: ""
        viewModel.setCategoryName(categoryId, categoryName)
        binding.cvNewRuleScreen.setContent {
            NewRulesScreen(
                viewModel,
                this::finish
            )
        }
        initStatusBarColor()
    }

    private fun initStatusBarColor() {
        window.statusBarColor = getColor(R.color.hous_blue_bg)
    }
}
