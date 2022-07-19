package com.hous.hous_aos.ui.newrules

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        binding.cvNewRuleScreen.setContent {
            NewRulesScreen(viewModel)
        }
    }
}
