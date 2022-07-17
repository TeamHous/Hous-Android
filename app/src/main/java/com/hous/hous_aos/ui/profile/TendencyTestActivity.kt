package com.hous.hous_aos.ui.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.ActivityTendencyTestBinding
import com.hous.hous_aos.databinding.DialogTypeTestStopBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TendencyTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTendencyTestBinding
    private lateinit var tendencyAdapter: TendencyAdapter
    val viewModel: TendencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tendency_test)
        setContentView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        tendencyAdapter = TendencyAdapter(
            select = viewModel::select,
            backPage = viewModel::backPage,
            nextPage = viewModel::nextPage,
            sendData = viewModel::sendData,
            showDialog = ::showDialog
        )
        binding.vpTendency.adapter = tendencyAdapter
        binding.vpTendency.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpTendency.isUserInputEnabled = false

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { tendencyAdapter.submitList(it.typeTests) }
            .launchIn(lifecycleScope)

        viewModel.move.observe(this) { move ->
            if (move) {
                if(binding.vpTendency.currentItem == 14){
                    val intent = Intent(this, TendencyResultActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                binding.vpTendency.currentItem += 1
            } else {
                binding.vpTendency.currentItem -= 1
            }
        }
    }

    private fun showDialog() {
        val dialogBinding = DialogTypeTestStopBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .create()
        dialog.window!!.decorView.background =
            ContextCompat.getDrawable(this, R.drawable.shape_white_fill_20_rect)
        dialogBinding.ivDialogClose.setOnClickListener { dialog.dismiss() }
        dialogBinding.tvDialogStop.setOnClickListener { finish() }
        dialog.show()
    }
}
