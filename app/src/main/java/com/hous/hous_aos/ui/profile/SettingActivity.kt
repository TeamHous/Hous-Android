package com.hous.hous_aos.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hous.hous_aos.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivSettingAlarm.setOnClickListener { it.isSelected = !it.isSelected }
    }
}
