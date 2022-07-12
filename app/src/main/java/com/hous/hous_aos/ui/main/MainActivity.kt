package com.hous.hous_aos.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityMainBinding
import com.hous.hous_aos.ui.home.HomeFragment
import com.hous.hous_aos.ui.profile.ProfileFragment
import com.hous.hous_aos.ui.rules.rule.RulesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBotNav()
    }

    private fun initBotNav() {
        binding.botNavMain.selectedItemId = R.id.ic_bot_nav_home
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.fcv_main)
        }

        binding.botNavMain.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.ic_bot_nav_home -> {
                        background = ContextCompat.getDrawable(
                            this@MainActivity,
                            R.drawable.shape_yellow_fill_20_rect
                        )
                        itemIconTintList = ContextCompat.getColorStateList(
                            this@MainActivity,
                            R.color.sel_bot_navi_home_color
                        )
                        itemTextColor = ContextCompat.getColorStateList(
                            this@MainActivity,
                            R.color.sel_bot_navi_home_color
                        )
                        binding.tvMainTitle.text = getString(R.string.home_title)
                        supportFragmentManager.commit {
                            replace<HomeFragment>(R.id.fcv_main)
                            setReorderingAllowed(true)
                        }
                        true
                    }
                    R.id.ic_bot_nav_rules -> {
                        background = ContextCompat.getDrawable(
                            this@MainActivity,
                            R.drawable.shape_blue_fill_20_rect
                        )
                        itemIconTintList = ContextCompat.getColorStateList(
                            this@MainActivity,
                            R.color.sel_bot_navi_rule_color
                        )
                        itemTextColor = ContextCompat.getColorStateList(
                            this@MainActivity,
                            R.color.sel_bot_navi_rule_color
                        )
                        binding.tvMainTitle.text = getString(R.string.rules_title)
                        supportFragmentManager.commit {
                            replace<RulesFragment>(R.id.fcv_main)
                            setReorderingAllowed(true)
                        }
                        true
                    }
                    else -> {
                        background = ContextCompat.getDrawable(
                            this@MainActivity,
                            R.drawable.shape_red_fill_20_rect
                        )
                        itemIconTintList = ContextCompat.getColorStateList(
                            this@MainActivity,
                            R.color.sel_bot_navi_profile_color
                        )
                        itemTextColor = ContextCompat.getColorStateList(
                            this@MainActivity,
                            R.color.sel_bot_navi_profile_color
                        )
                        binding.tvMainTitle.text = getString(R.string.profile_title)
                        supportFragmentManager.commit {
                            replace<ProfileFragment>(R.id.fcv_main)
                            setReorderingAllowed(true)
                        }
                        true
                    }
                }
            }
        }
    }
}
