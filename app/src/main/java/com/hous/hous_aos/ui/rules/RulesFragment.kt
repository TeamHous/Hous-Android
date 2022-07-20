package com.hous.hous_aos.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentRulesBinding
import com.hous.hous_aos.ui.rules.edit_category.EditCategoryFragment
import com.hous.hous_aos.ui.rules.my_to_do.MyToDoFragment
import com.hous.hous_aos.ui.rules.new_category.NewCategoryFragment
import com.hous.hous_aos.ui.rules.rules_table.RulesTableFragment
import com.hous.hous_aos.ui.rules.today_to_do.TodayToDoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RulesFragment : Fragment() {
    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding ?: error("null값 들어감")
    private lateinit var homeRulesCategoryAdapter: HomeRulesCategoryAdapter
    private val viewModel: RulesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rules, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransaction()
        initAdapter()
        observeCategory()
        onClickSmileIcon()
    }

    private fun initAdapter() {
        homeRulesCategoryAdapter = HomeRulesCategoryAdapter(
            ::onLongClickCategoryIcon,
            onCategoryClick = { onClickCategoryIcon() },
            onPlusClick = { onClickPlusIcon() },
            onChangeIsSelected = { setCategoryIsSelected(it) },
        )
        binding.rvRules.adapter = homeRulesCategoryAdapter
    }

    private fun observeCategory() {
        viewModel.categoryOfRuleList.observe(viewLifecycleOwner) {
            homeRulesCategoryAdapter.submitList(it.toList())
        }
    }

    private fun initTransaction() {
        when (viewModel.toDoViewType.value) {
            ToDoViewType.TODAY_TO_DO -> {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<TodayToDoFragment>(R.id.frg_bottom)
                }
            }
            ToDoViewType.MY_TO_DO -> {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<MyToDoFragment>(R.id.frg_bottom)
                }
            }
            else -> IllegalArgumentException("viewModel.toDoViewType.value: ${viewModel.toDoViewType.value}")
        }
    }

    /** to-do로 돌아가기*/
    private fun onClickSmileIcon() {
        binding.ivSmile.setOnClickListener {
            when (viewModel.isSelectedCategorySmile.value) {
                false -> {
                    viewModel.setSmileSelected(true)
                    viewModel.initCategorySelected()
                    parentFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<RulesFragment>(R.id.fcv_main)
                    }
                }
                true -> return@setOnClickListener
                else -> throw IllegalArgumentException("viewModel.isSelectedCategorySmile.value: ${viewModel.isSelectedCategorySmile.value}")
            }
        }
    }

    /** RulesTableFragment로 이동 */
    private fun onClickCategoryIcon() {
        viewModel.setSmileSelected(false)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RulesTableFragment>(R.id.frg_bottom)
        }
    }

    /** EditCategoryFragment로 이동 */
    private fun onLongClickCategoryIcon() {
        viewModel.setSmileSelected(false)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<EditCategoryFragment>(R.id.frg_bottom)
        }
    }

    /** 추가 Fragment로 이동 */
    private fun onClickPlusIcon() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NewCategoryFragment>(R.id.frg_bottom)
        }
    }

    private fun setCategoryIsSelected(position: Int) {
        viewModel.setCategorySelected(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val TAG = "RulesFragment"
    }
}
