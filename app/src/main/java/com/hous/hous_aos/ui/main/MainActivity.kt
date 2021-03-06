package com.hous.hous_aos.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityMainBinding
import com.hous.hous_aos.ui.home.HomeFragment
import com.hous.hous_aos.ui.profile.ProfileFragment
import com.hous.hous_aos.ui.rules.RulesFragment
import com.hous.hous_aos.ui.rules.RulesViewModel
import com.hous.hous_aos.ui.rules.ToDoViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RulesViewModel by viewModels()
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
                        binding.position = HOME
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
                        supportFragmentManager.commit {
                            replace<HomeFragment>(R.id.fcv_main)
                            setReorderingAllowed(true)
                        }
                        true
                    }
                    R.id.ic_bot_nav_rules -> {
                        binding.position = RULES
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
                        supportFragmentManager.commit {
                            viewModel.setSmileSelected(true)
                            viewModel.setToDoViewType(ToDoViewType.TODAY_TO_DO)
                            viewModel.initCategorySelected()
                            setReorderingAllowed(true)
                            replace<RulesFragment>(R.id.fcv_main)
                            //
                        }
                        true
                    }
                    else -> {
                        binding.position = PROFILE
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
                        supportFragmentManager.commit {
                            replace<ProfileFragment>(R.id.fcv_main)
                            setReorderingAllowed(true)
                        }
                        true
                    }
                }
            }
            setOnItemReselectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.ic_bot_nav_home -> Unit
                    R.id.ic_bot_nav_rules -> Unit
                    R.id.ic_bot_nav_profile -> Unit
                }
            }
        }
    }

    companion object {
        val HOME = 0
        val RULES = 1
        val PROFILE = 2
    }
}
