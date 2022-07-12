package com.hous.hous_aos.ui.rules.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentRulesBinding
import com.hous.hous_aos.ui.rules.adapter.CategoryOfRuleAdapter
import com.hous.hous_aos.util.showToast

class RulesFragment : Fragment() {
    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding ?: error("null값 들어감")
    private lateinit var categoryOfRuleAdapter: CategoryOfRuleAdapter
    private val categoryViewModel: CategoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rules, container, false)
        binding.viewmodel = categoryViewModel
        binding.lifecycleOwner = this@RulesFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeCategory()
    }

    private fun initAdapter() {
        categoryOfRuleAdapter = CategoryOfRuleAdapter(
            onCategoryClick = { onClickCategoryIcon() },
            onPlusClick = { onClickPlusIcon() },
            onChangeIsSelected = { onChangeIsSelected(it) }
        )
        binding.rvRules.adapter = categoryOfRuleAdapter
    }

    private fun observeCategory() {
        categoryViewModel.CategoryOfRuleList.observe(viewLifecycleOwner) {
            categoryOfRuleAdapter.submitList(it.toList())
        }
    }

    /** 임시로 토스트를 박아놈*/
    private fun onClickCategoryIcon() {
        requireActivity().showToast("살려줘 잠 좀 자고싶어!!!!")
    }

    private fun onClickPlusIcon() {
        requireActivity().showToast("sadasdsad!")
    }

    private fun onChangeIsSelected(position: Int) {
        categoryViewModel.onChangeIsSelected(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val TAG = "RulesFragment"
    }
}
