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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_roommate_card)
    }
}