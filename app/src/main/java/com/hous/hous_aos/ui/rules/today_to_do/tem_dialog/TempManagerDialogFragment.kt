package com.hous.hous_aos.ui.rules.today_to_do.tem_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentTempManagerBinding
import com.hous.hous_aos.ui.rules.RulesViewModel
import com.hous.hous_aos.util.showToast

class TempManagerFragment : DialogFragment() {
    private var _binding: FragmentTempManagerBinding? = null
    private val binding get() = _binding ?: error("binding에 null 들어감")
    private val viewModel: RulesViewModel by activityViewModels()
    private var tmpMangerAdapter: TempMangerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_temp_manager, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initDialog()
        clickCloseIcon()
        clickSaveButton()
        viewModel.fetchToTmpManagerList()
        observeHomieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        tmpMangerAdapter = null
    }

    private fun initAdapter() {
        tmpMangerAdapter = TempMangerAdapter(viewModel::setSelectedTmpManager)
        binding.rvTmpManagers.adapter = tmpMangerAdapter
    }

    private fun initDialog() {
        isCancelable = false
        dialog?.window!!.decorView.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.shape_white_fill_20_rect)
    }

    private fun clickCloseIcon() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun clickSaveButton() {
        binding.ivSaveTmpManager.setOnClickListener {
            requireContext().showToast("${viewModel.putToTmpManagerList().size}명 서버로 put해버리기~")
            viewModel.fetchToTodayToDoList()
            dismiss()
        }
    }

    private fun observeHomieList() {
        viewModel.tmpManagerList.observe(viewLifecycleOwner) {
            requireNotNull(tmpMangerAdapter).submitList(it.toList())
        }
    }
}
