package com.hous.hous_aos.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentEventDialogBinding
import com.hous.hous_aos.ui.home.adapter.EventParticipantAdapter
import java.util.Calendar

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
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        fetchToViewModel()
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

    /**
     * 0번째 아이디는 추가 다이얼로그 띄우게 하기*/
    private fun fetchToViewModel() {
        if (viewModel.eventIconPosition.value == 0) {
            viewModel.fetchToAddEventData()
        }
        Log.e(TAG, "           viewModel.eventDate : ${viewModel.eventDate.value} ")
//        binding.tvNumYear.text = viewModel.eventDate.value!!.substring(0, 4)
//        binding.tvNumMonth.text = viewModel.eventDate.value!!.substring(5, 7)
//        binding.tvNumDate.text = viewModel.eventDate.value!!.substring(8, 10)
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
            datePickerDialog.setCancelable(false)
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
            if (binding.btnDelete.text.equals("삭제")) {
                Log.d(TAG, "EventDialogFragment - deleteDialog() called")
                viewModel.deleteEventItem()
            }
            dialog?.dismiss()
        }
    }

    private fun saveDialog() {
        binding.clSave.setOnClickListener {
            when (binding.tvSave.text) {
                "저장" -> {
                    if (binding.edtEventName.text.isNotEmpty()) {
                        viewModel.putToEventParticipant()
                        dialog?.dismiss()
                    }
                }
                "추가" -> {
                    if (binding.edtEventName.text.isNotEmpty()) {
                        viewModel.addToEventParticipant()
                        dialog?.dismiss()
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "EVENT_DIALOG"
    }
}
