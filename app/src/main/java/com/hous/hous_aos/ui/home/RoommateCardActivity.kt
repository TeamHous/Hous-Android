package com.hous.hous_aos.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityRoommateCardBinding

class RoommateCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoommateCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_roommate_card)
    }
}