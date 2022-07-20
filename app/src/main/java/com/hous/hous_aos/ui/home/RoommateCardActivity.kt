package com.hous.hous_aos.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityRoommateCardBinding
import com.hous.hous_aos.ui.profile.ProfileViewModel
import com.hous.hous_aos.ui.profile.TendencyResultActivity
import com.hous.hous_aos.util.setDrawable

class RoommateCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoommateCardBinding
    val viewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_roommate_card)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvDetailTendency.setOnClickListener {
            val intent = Intent(this, TendencyResultActivity::class.java)
            intent.putExtra("data", "back") // "back"면 백버튼, "end"면 완료버튼
            startActivity(intent)
        }

        viewModel.profileData.observe(this) {
            }
        }
    }
}