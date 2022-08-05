package com.hous.hous_aos.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hous.hous_aos.databinding.ActivityTestInfoBinding

class TestInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, TendencyTestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
