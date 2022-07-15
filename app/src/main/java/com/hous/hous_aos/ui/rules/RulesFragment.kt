package com.hous.hous_aos.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentRulesBinding
import com.hous.hous_aos.ui.rules.today_to_do.TodayToDoFragment
import com.hous.hous_aos.util.showToast

class RulesFragment : Fragment() {
    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding ?: error("null값 들어감")
    private lateinit var homeRulesCategoryAdapter: HomeRulesCategoryAdapter
    private val viewModel: RulesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rules, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RulesFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransaction()
        changeTransaction()
        initAdapter()
        observeCategory()
        onClickSmileIcon()
    }

    private fun initAdapter() {
        homeRulesCategoryAdapter = HomeRulesCategoryAdapter(
            onCategoryClick = { onClickCategoryIcon() },
            onPlusClick = { onClickPlusIcon() },
            onChangeIsSelected = { setCategoryIsSelected(it) }
        )
        binding.rvRules.adapter = homeRulesCategoryAdapter
    }

    private fun observeCategory() {
        viewModel.categoryOfRuleList.observe(viewLifecycleOwner) {
            homeRulesCategoryAdapter.submitList(it.toList())
        }
    }

    private fun initTransaction() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TodayToDoFragment>(R.id.frg_bottom)
        }
    }

    private fun changeTransaction() {
        // TODO Category <-> ToDoFragment transaction 로직
    }

    // TODO 오늘의 to-do로 돌아가기
    private fun onClickSmileIcon() {
        binding.ivSmile.setOnClickListener {
            when (viewModel.isSelectedCategorySmile.value) {
                false -> viewModel.setSmileSelected()
                true -> requireContext().showToast("웃음웃음우스음~!!!!!!!!")
                else -> throw IllegalArgumentException("viewModel.isSelectedCategorySmile.value: ${viewModel.isSelectedCategorySmile.value}")
            }
        }
    }

    /** 임시로 토스트를 박아놈*/
    private fun onClickCategoryIcon() {
        // TODO ToDoTaskFragment로 이동
        requireActivity().showToast("ToDoTaskFragment로 이동")
    }

    private fun onClickPlusIcon() {
        // TODO 카테고리 수정 Fragment로 이동
        requireActivity().showToast(" 카테고리 수정 Fragment로 이동")
    }

    private fun setCategoryIsSelected(position: Int) {
        viewModel.setCategorySelected(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val TAG = "RulesFragment"
    }
}
