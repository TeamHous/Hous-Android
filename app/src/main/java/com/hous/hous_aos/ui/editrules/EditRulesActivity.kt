package com.hous.hous_aos.ui.editrules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hous.hous_aos.databinding.ActivityEditRulesBinding

class EditRulesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRulesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvEditRuleScreen.setContent { EditRulesScreen() }
    }
}
