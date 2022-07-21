package com.hous.hous_aos.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentEventDialogBinding
import com.hous.hous_aos.ui.home.adapter.EventParticipantAdapter
import java.util.*

class EventDialogFragment : DialogFragment() {

    private var _binding: FragmentEventDialogBinding? = null
    private val binding get() = _binding ?: error("binding에 null이 들어갔엉")
    private var eventParticipantAdapter: EventParticipantAdapter? = null
    private val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_dialog, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@EventDialogFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchToViewModel()
        initAdapter()
        initDialog()
        observeParticipateList()
        closeDialog()
        deleteDialog()
        saveDialog()
        clickDatePicker()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        eventParticipantAdapter = null
    }

    private fun fetchToViewModel() {
        if (viewModel.eventIconPosition.value == 0) {
            viewModel.fetchToAddEventData()
        } else {
            viewModel.fetchToResponseEventData()
        }
    }

    private fun initAdapter() {
        eventParticipantAdapter = EventParticipantAdapter(viewModel::setSelectedEventParticipant)
        binding.rvParticipant.adapter = eventParticipantAdapter
    }

    private fun clickDatePicker() {
        binding.clDates.setOnClickListener {
            val date: String = viewModel.eventDate.value!!
            val dateList = date.split("-")
            val year = dateList[0].toInt()
            val month = dateList[1].toInt()
            val day = dateList[2].toInt()

            val cal = Calendar.getInstance() // 캘린더뷰 만들기
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    cal.set(year, month, dayOfMonth)
                    val year = year.toString()
                    val month = if (month < 9) {
                        ("0" + (month + 1).toString())
                    } else {
                        (month + 1).toString()
                    }
                    val dayOfMonth = if (dayOfMonth < 10) {
                        "0$dayOfMonth"
                    } else {
                        dayOfMonth.toString()
                    }
                    val date = "$year-$month-$dayOfMonth"
                    viewModel.setEventData(date)
                }
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.MyDatePickerDialogTheme_Spinner,
                dateSetListener,
                year,
                month - 1,
                day
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()
        }
    }

    private fun initDialog() {
        isCancelable = false
        dialog?.window!!.decorView.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.shape_white_fill_20_rect)
    }

    private fun observeParticipateList() {
        viewModel.eventParticipantList.observe(viewLifecycleOwner) {
            requireNotNull(eventParticipantAdapter).submitList(it.toList())
        }
    }

    private fun closeDialog() {
        binding.ivClose.setOnClickListener {
            Log.d(TAG, "EventDialogFragment - closeDialog() called")
            dialog?.dismiss()
        }
    }

    private fun deleteDialog() {
        binding.btnDelete.setOnClickListener {
            Log.d(TAG, "EventDialogFragment - deleteDialog() called")
            // 삭제로직 추가 viewModel.deleteEventData??
            viewModel.fetchToResponseEventData()
            dialog?.dismiss()
        }
    }

    private fun saveDialog() {
        binding.clSave.setOnClickListener {
            Log.d(TAG, "EventDialogFragment - saveDialog() called")
            if (binding.edtEventName.text.isNotEmpty()) {
                viewModel.putToEventParticipant()
                dialog?.dismiss()
            }
        }
    }

    companion object {
        private const val TAG = "EVENT_DIALOG"
    }
}
