package com.hous.hous_aos.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBotNav()
    }

    private fun initBotNav() {
        binding.botNavMain.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.ic_bot_nav_home -> {
                        background = ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_bot_navi_home)
                        itemIconTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.sel_bot_navi_home_color)
                        itemTextColor = ContextCompat.getColorStateList(this@MainActivity, R.color.sel_bot_navi_home_color)
                        true
                    }
                    R.id.ic_bot_nav_rules -> {
                        background = ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_bot_navi_rule)
                        itemIconTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.sel_bot_navi_rule_color)
                        itemTextColor = ContextCompat.getColorStateList(this@MainActivity, R.color.sel_bot_navi_rule_color)
                        true
                    }
                    else -> {
                        background = ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_bot_navi_profile)
                        itemIconTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.sel_bot_navi_profile_color)
                        itemTextColor = ContextCompat.getColorStateList(this@MainActivity, R.color.sel_bot_navi_profile_color)
                        true
                    }
                }
            }
        }
    }
}