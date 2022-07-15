package com.hous.hous_aos.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityTendencyTestBinding

class TendencyTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTendencyTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tendency_test)
    }
}