package com.hous.hous_aos.ui.home

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
        viewModel.setParticipantList()
        viewModel.setEventName()
        initAdapter()
        initDialog()
        observeParticipateList()
        closeDialog()
        deleteDialog()
        saveDialog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        eventParticipantAdapter = null
    }

    private fun initAdapter() {
        eventParticipantAdapter = EventParticipantAdapter(viewModel::setSelectedEventParticipant)
        binding.rvParticipant.adapter = eventParticipantAdapter
    }

    private fun clickDatePicker() {
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
//            requireContext().showToast("${viewModel.putToTmpManagerList().size}명 서버로 put해버리기~")
            dialog?.dismiss()
        }
    }

    private fun saveDialog() {
        binding.btnSave.setOnClickListener {
            Log.d(TAG, "EventDialogFragment - saveDialog() called")
//            requireContext().showToast("${viewModel.putToTmpManagerList().size}명 서버로 put해버리기~")
            dialog?.dismiss()
        }
    }

    companion object {
        private const val TAG = "EVENT_DIALOG"
    }
}
