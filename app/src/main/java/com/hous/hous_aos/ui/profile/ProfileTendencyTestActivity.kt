package com.hous.hous_aos.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityProfileTendencyTestBinding

class ProfileTendencyTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileTendencyTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_tendency_test)
    }
}